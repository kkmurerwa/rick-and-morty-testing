package com.murerwa.rickandmortytesting.data.repositories

import com.murerwa.rickandmortytesting.data.network.ApiClient
import com.murerwa.rickandmortytesting.data.network.BaseRepository
import com.murerwa.rickandmortytesting.data.network.NetworkResult
import com.murerwa.rickandmortytesting.domain.models.common.ItemsResponse
import com.murerwa.rickandmortytesting.domain.models.episodes.Episode
import com.murerwa.rickandmortytesting.domain.repositories.EpisodesRepository
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient
): EpisodesRepository, BaseRepository()  {
    override suspend fun getEpisodes(page: Int): NetworkResult<ItemsResponse<Episode>> {
        return safeApiCall { apiClient.getEpisodes(page) }
    }
}