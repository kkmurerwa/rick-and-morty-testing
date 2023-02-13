package com.murerwa.rickandmortytesting.features.episodes.data.api

import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.Urls
import com.murerwa.rickandmortytesting.features.episodes.domain.model.Episode
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesApiClient {
    @GET(Urls.EPISODES)
    suspend fun getEpisodes(
        @Query("page") page: Int,
    ): ItemsResponse<Episode>
}