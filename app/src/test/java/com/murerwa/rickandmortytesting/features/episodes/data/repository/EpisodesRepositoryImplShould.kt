package com.murerwa.rickandmortytesting.features.episodes.data.repository

import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.ApiClient
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.features.episodes.domain.model.Episode
import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EpisodesRepositoryImplShould: BaseUnitTest() {
    private val apiClient = mock<ApiClient>()
    private val repository = EpisodesRepositoryImpl(apiClient)
    private val mockResponse = mock<ItemsResponse<Episode>>()

    @Test
    fun invokeApiClientGetEpisodes() = runTest {
        whenever(apiClient.getEpisodes(any())).thenReturn(mockResponse)

        repository.getEpisodes(1)

        verify(apiClient, times(1)).getEpisodes(1)
    }

    @Test
    fun getEpisodesFromRepositoryAsNetworkResultInstance() = runTest {
        whenever(apiClient.getEpisodes(any())).thenReturn(mockResponse)

        val actual = repository.getEpisodes(1)

        assertEquals(NetworkResult.Success(mockResponse), actual)
    }
}