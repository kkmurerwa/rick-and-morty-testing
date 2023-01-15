package com.murerwa.rickandmortytesting.domain.repositories

import com.murerwa.rickandmortytesting.data.network.NetworkResult
import com.murerwa.rickandmortytesting.domain.models.characters.Character
import com.murerwa.rickandmortytesting.domain.models.characters.Location
import com.murerwa.rickandmortytesting.domain.models.common.ItemsResponse
import com.murerwa.rickandmortytesting.domain.models.episodes.Episode

interface LocationsRepository {

    suspend fun getLocations(page: Int): NetworkResult<ItemsResponse<Location>>

}