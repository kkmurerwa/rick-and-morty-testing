package com.murerwa.rickandmortytesting.features.characters.data.repository

import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.features.characters.data.api.CharactersApiClient
import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersRepositoryImplShould: BaseUnitTest() {
    private val apiClient = mock<CharactersApiClient>()
    private val repository = CharactersRepositoryImpl(apiClient)
    private val mockResponse = mock<ItemsResponse<Character>>()

    @Test
    fun invokeApiClientGetCharacters() = runTest {
        whenever(apiClient.getCharacters(any())).thenReturn(mockResponse)

        repository.getCharacters(1)

        verify(apiClient, times(1)).getCharacters(1)
    }

    @Test
    fun getCharactersFromRepositoryAsNetworkResultInstance() = runTest {
        whenever(apiClient.getCharacters(any())).thenReturn(mockResponse)

        val actual = repository.getCharacters(1)

        assertEquals(NetworkResult.Success(mockResponse), actual)
    }

    @Test
    fun invokeApiClientGetCharacterDetails() = runTest {
        val character = mock<Character>()
        whenever(apiClient.getCharacterDetails(any())).thenReturn(character)

        repository.getCharacterDetails(1)

        verify(apiClient, times(1)).getCharacterDetails(1)
    }

    @Test
    fun getCharacterDetailsFromRepositoryAsNetworkResultInstance() = runTest {
        val character = mock<Character>()
        whenever(apiClient.getCharacterDetails(any())).thenReturn(character)

        val actual = repository.getCharacterDetails(1)

        assertEquals(NetworkResult.Success(character), actual)
    }
}