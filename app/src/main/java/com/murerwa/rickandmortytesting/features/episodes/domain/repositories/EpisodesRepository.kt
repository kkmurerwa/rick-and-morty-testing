package com.murerwa.rickandmortytesting.features.episodes.domain.repositories

import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.features.episodes.domain.models.Episode

interface EpisodesRepository {

    suspend fun getEpisodes(page: Int): NetworkResult<ItemsResponse<Episode>>

}