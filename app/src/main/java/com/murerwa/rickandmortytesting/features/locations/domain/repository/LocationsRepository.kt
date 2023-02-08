package com.murerwa.rickandmortytesting.features.locations.domain.repository

import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.features.locations.domain.models.Location
import com.murerwa.rickandmortytesting.core.models.ItemsResponse

interface LocationsRepository {

    suspend fun getLocations(page: Int): NetworkResult<ItemsResponse<Location>>

}