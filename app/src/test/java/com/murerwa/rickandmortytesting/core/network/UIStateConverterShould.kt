package com.murerwa.rickandmortytesting.core.network

import android.app.Application
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.features.characters.domain.repositories.CharactersRepository
import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UIStateConverterShould: BaseUnitTest() {
    private val mockRepository: CharactersRepository = mock()
    private val mockApp: Application = mock()
    private val mockResponse = mock<ItemsResponse<Character>>()

    @Test
    fun returnUIStateSuccessObjectWhenConvertToUiStateCalledWithNetworkSuccessObject() = runTest {
        whenever(mockRepository.getCharacters(any())).thenReturn(
            NetworkResult.Success(mockResponse)
        )

        val uiState = convertToUIState(
            response = mockRepository.getCharacters(page = 1),
            app = mockApp,
            errorMessage = "test message"
        )

        assertTrue(uiState is UIState.Success)
    }

    @Test
    fun returnUIStateErrorObjectWhenConvertToUiStateCalledWithNetworkResultFailureObject() = runTest {
        whenever(mockRepository.getCharacters(any())).thenReturn(
            NetworkResult.Failure(
                true,
                404,
                ResponseBody.create(null, "test body")
            )
        )

        val uiState = convertToUIState(
            response = mockRepository.getCharacters(page = 1),
            app = mockApp,
            errorMessage = "test message"
        )

        assertTrue(uiState is UIState.Error)
    }
}