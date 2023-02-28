package com.murerwa.rickandmortytesting.features.locations.data.repository

import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.features.locations.data.api.LocationsApiClient
import com.murerwa.rickandmortytesting.features.locations.domain.model.Location
import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocationsRepositoryImplShould: BaseUnitTest() {
    private val apiClient = mock<LocationsApiClient>()
    private val repository = LocationsRepositoryImpl(apiClient)
    private val mockLocation = mock<Location>()
    private val mockResponse = mock<ItemsResponse<Location>>()

    @Test
    fun invokeApiClientGetLocations() = runTest {
        whenever(apiClient.getLocations(any())).thenReturn(mockResponse)

        repository.getLocations(1)

        verify(apiClient, times(1)).getLocations(1)
    }

    @Test
    fun getLocationsFromRepositoryAsNetworkResultInstance() = runTest {
        whenever(apiClient.getLocations(any())).thenReturn(mockResponse)

        val actual = repository.getLocations(1)

        assertEquals(NetworkResult.Success(mockResponse), actual)
    }

    @Test
    fun invokeApiClientGetLocationDetails() = runTest {
        whenever(apiClient.getLocationDetails(any())).thenReturn(mockLocation)

        repository.getLocationDetails(1)

        verify(apiClient, times(1)).getLocationDetails(1)
    }

    @Test
    fun getLocationDetailsFromRepositoryAsNetworkResultInstance() = runTest {
        whenever(apiClient.getLocationDetails(any())).thenReturn(mockLocation)

        val actual = repository.getLocationDetails(1)

        assertEquals(NetworkResult.Success(mockLocation), actual)
    }
}