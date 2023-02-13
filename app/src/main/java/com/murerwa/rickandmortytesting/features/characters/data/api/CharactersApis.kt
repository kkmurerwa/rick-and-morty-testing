package com.murerwa.rickandmortytesting.features.characters.data.api

import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.Urls
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApis {
    @GET(Urls.CHARACTERS)
    suspend fun getCharacters(
        @Query("page") page: Int = 1,
    ): ItemsResponse<Character>

    @GET("${Urls.CHARACTERS}/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id: Int
    ): Character
}