package com.murerwa.rickandmortytesting.features.characters.presentation

import android.app.Application
import com.murerwa.rickandmortytesting.core.models.Info
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.core.network.convertToUIState
import com.murerwa.rickandmortytesting.features.characters.domain.repositories.CharactersRepository
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import com.murerwa.rickandmortytesting.utils.captureValues
import com.murerwa.rickandmortytesting.utils.getValueForTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModelShould: BaseUnitTest() {
    private val app = mock<Application>()
    private val repository = mock<CharactersRepository>()
    private val mockInfo = mock<Info>()
//    private val viewModel = CharactersViewModel(app, repository)

//    private val mockNetworkResponse =
//        mock<NetworkResult<ItemsResponse<Character>>>()
//
    private val mockNetworkResponse =
        NetworkResult.Success(ItemsResponse(mockInfo, listOf<Character>()))

    private val mockLiveDataResponse =
        mock<UIState<ItemsResponse<Character>>>()

    @Test
    fun getCharacters() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.getCharacters()

//        assertEquals(mockLiveDataResponse, values.last())
        assertEquals(mockLiveDataResponse, viewModel.charactersResponse.getValueForTest())

//        viewModel.getPlaylistDetails(playlistId)
//
//        assertEquals(expected, viewModel.playlistDetails.getValueForTest())
//
//        viewModel.charactersResponse.captureValues {
//            viewModel.charactersResponse.getValueForTest()
//
//            viewModel.getCharacters()
//
//            assertEquals(mockLiveDataResponse, values.last())
//        }
    }

    private fun mockSuccessfulCase(): CharactersViewModel {
        runBlocking {
            whenever(
                convertToUIState(
                    response = mockNetworkResponse,
                    app = app,
                    errorMessage = ""
                )
            ).thenReturn(
                mockLiveDataResponse
            )

            whenever(app.getString(any())).thenReturn("")

            whenever(repository.getCharacters(any())).thenReturn(mockNetworkResponse)
        }

        return CharactersViewModel(app, repository)
    }
}