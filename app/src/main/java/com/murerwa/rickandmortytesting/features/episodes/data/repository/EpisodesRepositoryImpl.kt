package com.murerwa.rickandmortytesting.features.episodes.data.repository

import com.murerwa.rickandmortytesting.core.network.BaseRepository
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.features.episodes.data.api.EpisodesApiClient
import com.murerwa.rickandmortytesting.features.episodes.domain.model.Episode
import com.murerwa.rickandmortytesting.features.episodes.domain.repositories.EpisodesRepository
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val apiClient: EpisodesApiClient
): EpisodesRepository, BaseRepository()  {
    override suspend fun getEpisodes(page: Int): NetworkResult<ItemsResponse<Episode>> {
        return safeApiCall { apiClient.getEpisodes(page) }
    }
}