package com.murerwa.rickandmortytesting.core.network

import android.app.Application
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.features.episodes.domain.repositories.EpisodesRepository
import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UIStateConverterTest: BaseUnitTest() {
    private val mockRepository: EpisodesRepository = mock()
    private val mockApp: Application = mock()

    @Test
    fun `should return UIState object when convertToUiState called`() = runTest {
        val uiState = convertToUIState(
            response = mockRepository.getEpisodes(page = 1),
            app = mockApp,
            errorMessage = "test message"
        )

        assertTrue(uiState is UIState.Success)
    }
}