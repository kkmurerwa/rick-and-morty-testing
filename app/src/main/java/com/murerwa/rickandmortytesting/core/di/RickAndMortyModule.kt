package com.murerwa.rickandmortytesting.core.di

import com.jakewharton.espresso.OkHttp3IdlingResource
import com.murerwa.rickandmortytesting.features.characters.data.repository.CharactersRepositoryImpl
import com.murerwa.rickandmortytesting.core.network.ApiClient
import com.murerwa.rickandmortytesting.core.network.Urls
import com.murerwa.rickandmortytesting.features.characters.data.api.CharactersApiClient
import com.murerwa.rickandmortytesting.features.episodes.data.repository.EpisodesRepositoryImpl
import com.murerwa.rickandmortytesting.features.locations.data.repository.LocationsRepositoryImpl
import com.murerwa.rickandmortytesting.features.characters.domain.repositories.CharactersRepository
import com.murerwa.rickandmortytesting.features.episodes.data.api.EpisodesApiClient
import com.murerwa.rickandmortytesting.features.episodes.domain.repositories.EpisodesRepository
import com.murerwa.rickandmortytesting.features.locations.data.api.LocationsApiClient
import com.murerwa.rickandmortytesting.features.locations.domain.repository.LocationsRepository
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
    fun charactersApiClient(retrofit: Retrofit): CharactersApiClient =
        retrofit.create(CharactersApiClient::class.java)

    @Provides
    fun episodesApiClient(retrofit: Retrofit): EpisodesApiClient =
        retrofit.create(EpisodesApiClient::class.java)

    @Provides
    fun locationsApiClient(retrofit: Retrofit): LocationsApiClient =
        retrofit.create(LocationsApiClient::class.java)

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Urls.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providesCharactersRepository(
        apiClient: CharactersApiClient
    ): CharactersRepository = CharactersRepositoryImpl(apiClient)

    @Provides
    fun providesEpisodesRepository(
        apiClient: EpisodesApiClient
    ): EpisodesRepository = EpisodesRepositoryImpl(apiClient)

    @Provides
    fun providesLocationsRepository(
        apiClient: LocationsApiClient
    ): LocationsRepository = LocationsRepositoryImpl(apiClient)

    @Provides
    fun providesIdlingResource() = idlingResource
}