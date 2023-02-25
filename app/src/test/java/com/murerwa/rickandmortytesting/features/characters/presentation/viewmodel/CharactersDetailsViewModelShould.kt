package com.murerwa.rickandmortytesting.features.characters.presentation.viewmodel

import android.app.Application
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.models.Info
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.features.characters.domain.repositories.CharactersRepository
import com.murerwa.rickandmortytesting.features.characters.presentation.viewmodels.CharactersDetailsViewModel
import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import com.murerwa.rickandmortytesting.utils.getValueForTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersDetailsViewModelShould: BaseUnitTest() {
    private val app = mock<Application>()
    private val repository = mock<CharactersRepository>()
    private val mockInfo = mock<Info>()
    private val mockCharacter = mock<Character>()

    private val mockNetworkResponseSuccess =
        NetworkResult.Success(mockCharacter)

    private val mockNetworkResponseFailure = NetworkResult.Failure(
        isNetworkError = false,
        errorCode = 10,
        errorBody = null
    )

    private val mockLiveDataResponseSuccess =
        UIState.Success(mockCharacter)

    private val mockLiveDataResponseFailure =
        UIState.Error("test", false)

    @Test
    fun getCharacterDetails() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.getCharacterDetails(1)

        assertEquals(mockLiveDataResponseSuccess, viewModel.characterDetailsResponse.getValueForTest())
    }

    @Test
    fun getCharacterDetailsError() = runTest {
        val viewModel = mockErrorCase()

        viewModel.getCharacterDetails(1)

        assertEquals(mockLiveDataResponseFailure, viewModel.characterDetailsResponse.getValueForTest())
    }

    private fun mockSuccessfulCase(): CharactersDetailsViewModel {
        runBlocking {
            whenever(app.getString(R.string.network_error_no_items_found))
                .thenReturn("test")

            whenever(repository.getCharacterDetails(any()))
                .thenReturn(mockNetworkResponseSuccess)
        }

        return CharactersDetailsViewModel(app, repository)
    }

    private fun mockErrorCase(): CharactersDetailsViewModel {
        runBlocking {
            whenever(app.getString(R.string.network_error_no_items_found))
                .thenReturn("test")

            whenever(repository.getCharacterDetails(any()))
                .thenReturn(mockNetworkResponseFailure)
        }

        return CharactersDetailsViewModel(app, repository)
    }
}