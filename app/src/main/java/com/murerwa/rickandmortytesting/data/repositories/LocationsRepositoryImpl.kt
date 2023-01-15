package com.murerwa.rickandmortytesting.data.repositories

import com.murerwa.rickandmortytesting.data.network.ApiClient
import com.murerwa.rickandmortytesting.data.network.BaseRepository
import com.murerwa.rickandmortytesting.data.network.NetworkResult
import com.murerwa.rickandmortytesting.domain.models.characters.Location
import com.murerwa.rickandmortytesting.domain.models.common.ItemsResponse
import com.murerwa.rickandmortytesting.domain.repositories.LocationsRepository
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor (
    private val apiClient: ApiClient
): LocationsRepository, BaseRepository() {
    override suspend fun getLocations(page: Int): NetworkResult<ItemsResponse<Location>> {
        return safeApiCall { apiClient.getLocations(page) }
    }
}