package com.murerwa.rickandmortytesting.features.locations.presentation

import android.app.Application
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.models.Info
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.features.locations.domain.model.Location
import com.murerwa.rickandmortytesting.features.locations.domain.repository.LocationsRepository
import com.murerwa.rickandmortytesting.features.locations.presentation.viewmodels.LocationsViewModel
import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import com.murerwa.rickandmortytesting.utils.getValueForTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocationsViewModelShould: BaseUnitTest() {
    private val app = mock<Application>()
    private val repository = mock<LocationsRepository>()
    private val mockInfo = mock<Info>()
    private val mockCharactersList = listOf<Location>(mock())

    private val mockNetworkResponseSuccess =
        NetworkResult.Success(ItemsResponse(mockInfo, mockCharactersList))

    private val mockNetworkResponseFailure = NetworkResult.Failure(
        isNetworkError = false,
        errorCode = 10,
        errorBody = null
    )

    private val mockLiveDataResponseSuccess =
        UIState.Success(ItemsResponse(mockInfo, mockCharactersList))

    private val mockLiveDataResponseFailure =
        UIState.Error("test", false)

    @Test
    fun getLocationsSuccess() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.getLocations()

        assertEquals(mockLiveDataResponseSuccess, viewModel.locationsResponse.getValueForTest())
    }

    @Test
    fun getLocationsFailure() = runTest {
        val viewModel = mockErrorCase()

        viewModel.getLocations()

        assertEquals(mockLiveDataResponseFailure, viewModel.locationsResponse.getValueForTest())
    }

    private fun mockSuccessfulCase(): LocationsViewModel {
        runBlocking {
            whenever(app.getString(R.string.network_error_no_items_found)).thenReturn("test")
            whenever(repository.getLocations(any())).thenReturn(mockNetworkResponseSuccess)
        }

        return LocationsViewModel(app, repository)
    }

    private fun mockErrorCase(): LocationsViewModel {
        runBlocking {
            whenever(app.getString(R.string.network_error_no_items_found)).thenReturn("test")
            whenever(repository.getLocations(any())).thenReturn(mockNetworkResponseFailure)
        }

        return LocationsViewModel(app, repository)
    }
}