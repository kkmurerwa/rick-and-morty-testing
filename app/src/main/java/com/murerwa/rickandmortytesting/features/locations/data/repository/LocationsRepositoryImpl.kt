package com.murerwa.rickandmortytesting.features.locations.data.repository

import com.murerwa.rickandmortytesting.core.network.BaseRepository
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.features.locations.domain.model.Location
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.features.locations.data.api.LocationsApiClient
import com.murerwa.rickandmortytesting.features.locations.domain.repository.LocationsRepository
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor (
    private val apiClient: LocationsApiClient
): LocationsRepository, BaseRepository() {
    override suspend fun getLocations(page: Int): NetworkResult<ItemsResponse<Location>> {
        return safeApiCall { apiClient.getLocations(page) }
    }

    override suspend fun getLocationDetails(id: Int): NetworkResult<Location> {
        return safeApiCall { apiClient.getLocationDetails(id) }
    }
}