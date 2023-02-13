package com.murerwa.rickandmortytesting.utils

import com.murerwa.rickandmortytesting.features.characters.data.api.CharactersApiClient
import com.murerwa.rickandmortytesting.features.episodes.data.api.EpisodesApiClient
import com.murerwa.rickandmortytesting.features.locations.data.api.LocationsApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    /**
     * as dependency injection is not used in this project for brevity,
     * static object initialization is done for demo purpose.
     *
     * @param url: Base url for apis
     * @return TestApis: Instance of the test api retrofit interface
     **/
    fun testCharactersApiInstance(url: String): CharactersApiClient {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersApiClient::class.java)
    }

    fun testEpisodesApiInstance(url: String): EpisodesApiClient {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EpisodesApiClient::class.java)
    }

    fun testLocationsApiInstance(url: String): LocationsApiClient {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationsApiClient::class.java)
    }
}