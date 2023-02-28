package com.murerwa.rickandmortytesting.features.locations.domain.repository

import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.features.locations.domain.model.Location
import com.murerwa.rickandmortytesting.core.models.ItemsResponse

interface LocationsRepository {

    suspend fun getLocations(page: Int): NetworkResult<ItemsResponse<Location>>

    suspend fun getLocationDetails(id: Int): NetworkResult<Location>

}