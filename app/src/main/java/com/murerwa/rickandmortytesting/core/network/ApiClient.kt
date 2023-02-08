package com.murerwa.rickandmortytesting.core.network

import com.murerwa.rickandmortytesting.features.episodes.domain.models.Episode
import com.murerwa.rickandmortytesting.features.characters.domain.models.Character
import com.murerwa.rickandmortytesting.features.locations.domain.models.Location
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET(Urls.CHARACTERS)
    suspend fun getCharacters(
        @Query("page") page: Int,
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