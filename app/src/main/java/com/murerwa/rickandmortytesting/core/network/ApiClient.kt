package com.murerwa.rickandmortytesting.core.network

import com.murerwa.rickandmortytesting.features.episodes.domain.model.Episode
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.features.locations.domain.model.Location
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET(Urls.CHARACTERS)
    suspend fun getCharacters(
        @Query("page") page: Int = 1,
    ): ItemsResponse<Character>

    @GET("${Urls.CHARACTERS}/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id: Int
    ): Character

    @GET(Urls.EPISODES)
    suspend fun getEpisodes(
        @Query("page") page: Int,
    ): ItemsResponse<Episode>

    @GET(Urls.LOCATIONS)
    suspend fun getLocations(
        @Query("page") page: Int,
    ): ItemsResponse<Location>
}