package com.murerwa.rickandmortytesting.data.module

import com.jakewharton.espresso.OkHttp3IdlingResource
import com.murerwa.rickandmortytesting.domain.network.ApiClient
import com.murerwa.rickandmortytesting.domain.network.Urls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class RickAndMortyModule {
    @Provides
    fun apiClient(retrofit: Retrofit): ApiClient =
        retrofit.create(ApiClient::class.java)

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Urls.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}