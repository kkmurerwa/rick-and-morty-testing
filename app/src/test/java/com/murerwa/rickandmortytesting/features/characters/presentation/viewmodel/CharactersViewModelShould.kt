package com.murerwa.rickandmortytesting.features.characters.presentation.viewmodel

import android.app.Application
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.models.Info
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.features.characters.domain.repositories.CharactersRepository
import com.murerwa.rickandmortytesting.features.characters.presentation.viewmodels.CharactersViewModel
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
class CharactersViewModelShould: BaseUnitTest() {
    private val app = mock<Application>()
    private val repository = mock<CharactersRepository>()
    private val mockInfo = mock<Info>()
    private val mockCharactersList = listOf<Character>(mock())

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
    fun getCharacters() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.getCharacters()

        assertEquals(mockLiveDataResponseSuccess, viewModel.charactersResponse.getValueForTest())
    }

    @Test
    fun getCharactersError() = runTest {
        val viewModel = mockErrorCase()

        viewModel.getCharacters()

        assertEquals(mockLiveDataResponseFailure, viewModel.charactersResponse.getValueForTest())
    }

    private fun mockSuccessfulCase(): CharactersViewModel {
        runBlocking {
            whenever(app.getString(R.string.network_error_no_items_found)).thenReturn("test")
            whenever(repository.getCharacters(any())).thenReturn(mockNetworkResponseSuccess)
        }

        return CharactersViewModel(app, repository)
    }

    private fun mockErrorCase(): CharactersViewModel {
        runBlocking {
            whenever(app.getString(R.string.network_error_no_items_found)).thenReturn("test")
            whenever(repository.getCharacters(any())).thenReturn(mockNetworkResponseFailure)
        }

        return CharactersViewModel(app, repository)
    }
}