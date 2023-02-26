package com.murerwa.rickandmortytesting.features.episodes.presentation

import android.app.Application
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.models.Info
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.features.episodes.domain.model.Episode
import com.murerwa.rickandmortytesting.features.episodes.domain.repositories.EpisodesRepository
import com.murerwa.rickandmortytesting.features.episodes.presentation.viewmodels.EpisodesViewModel
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
class EpisodesViewModelShould: BaseUnitTest() {
    private val app = mock<Application>()
    private val repository = mock<EpisodesRepository>()
    private val mockInfo = mock<Info>()
    private val mockEpisodesList = listOf<Episode>(mock())

    private val mockNetworkResponseSuccess =
        NetworkResult.Success(ItemsResponse(mockInfo, mockEpisodesList))

    private val mockNetworkResponseFailure = NetworkResult.Failure(
        isNetworkError = false,
        errorCode = 10,
        errorBody = null
    )

    private val mockLiveDataResponseSuccess =
        UIState.Success(ItemsResponse(mockInfo, mockEpisodesList))

    private val mockLiveDataResponseFailure =
        UIState.Error("test", false)


    @Test
    fun getEpisodesSuccess() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.getEpisodes()

        assertEquals(mockLiveDataResponseSuccess, viewModel.episodesResponse.getValueForTest())
    }

    @Test
    fun getEpisodesError() = runTest {
        val viewModel = mockErrorCase()

        viewModel.getEpisodes()

        assertEquals(mockLiveDataResponseFailure, viewModel.episodesResponse.getValueForTest())
    }

    private fun mockSuccessfulCase(): EpisodesViewModel {
        runBlocking {
            whenever(app.getString(R.string.network_error_no_items_found)).thenReturn("test")
            whenever(repository.getEpisodes(any())).thenReturn(mockNetworkResponseSuccess)
        }

        return EpisodesViewModel(app, repository)
    }

    private fun mockErrorCase(): EpisodesViewModel {
        runBlocking {
            whenever(app.getString(R.string.network_error_no_items_found)).thenReturn("test")
            whenever(repository.getEpisodes(any())).thenReturn(mockNetworkResponseFailure)
        }

        return EpisodesViewModel(app, repository)
    }
}