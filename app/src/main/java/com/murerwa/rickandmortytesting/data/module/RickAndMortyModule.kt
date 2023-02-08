package com.murerwa.rickandmortytesting.data.module

import com.jakewharton.espresso.OkHttp3IdlingResource
import com.murerwa.rickandmortytesting.data.repositories.CharactersRepositoryImpl
import com.murerwa.rickandmortytesting.data.network.ApiClient
import com.murerwa.rickandmortytesting.data.network.Urls
import com.murerwa.rickandmortytesting.data.repositories.EpisodesRepositoryImpl
import com.murerwa.rickandmortytesting.data.repositories.LocationsRepositoryImpl
import com.murerwa.rickandmortytesting.domain.repositories.CharactersRepository
import com.murerwa.rickandmortytesting.domain.repositories.EpisodesRepository
import com.murerwa.rickandmortytesting.domain.repositories.LocationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import org.intellij.lang.annotations.PrintFormat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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

    @Provides
    fun providesCharactersRepository(
        apiClient: ApiClient
    ): CharactersRepository = CharactersRepositoryImpl(apiClient)

    @Provides
    fun providesEpisodesRepository(
        apiClient: ApiClient
    ): EpisodesRepository = EpisodesRepositoryImpl(apiClient)

    @Provides
    fun providesIdlingResource() = idlingResource

    @Provides
    fun providesLocationsRepository(
        apiClient: ApiClient
    ): LocationsRepository = LocationsRepositoryImpl(apiClient)
}