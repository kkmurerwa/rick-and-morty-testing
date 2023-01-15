package com.murerwa.rickandmortytesting.data.network

import com.murerwa.rickandmortytesting.domain.models.episodes.Episode
import com.murerwa.rickandmortytesting.domain.models.characters.Character
import com.murerwa.rickandmortytesting.domain.models.characters.Location
import com.murerwa.rickandmortytesting.domain.models.common.ItemsResponse
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

    @GET
    suspend fun getEpisodes(
        @Query("page") page: Int,
    ): ItemsResponse<Episode>

    @GET
    suspend fun getLocations(
        @Query("page") page: Int,
    ): ItemsResponse<Location>
}