package com.murerwa.rickandmortytesting.features.characters.data.api

import com.google.gson.Gson
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.features.characters.data.api.CharactersApiClient
import com.murerwa.rickandmortytesting.features.characters.data.repository.CharactersRepositoryImpl
import com.murerwa.rickandmortytesting.features.episodes.data.api.EpisodesApiClient
import com.murerwa.rickandmortytesting.features.episodes.data.repository.EpisodesRepositoryImpl
import com.murerwa.rickandmortytesting.features.locations.data.api.LocationsApiClient
import com.murerwa.rickandmortytesting.features.locations.data.repository.LocationsRepositoryImpl
import com.murerwa.rickandmortytesting.fixtures.*
import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import com.murerwa.rickandmortytesting.utils.RetrofitHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersApiClientShould: BaseUnitTest() {
    private val characters = listOf(tCharacter)

    private lateinit var testCharactersApis: CharactersApiClient

    private lateinit var mockWebServer: MockWebServer
    private lateinit var charactersRepository: CharactersRepositoryImpl

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        testCharactersApis = RetrofitHelper.testCharactersApiInstance(mockWebServer.url("/").toString())

        charactersRepository = CharactersRepositoryImpl(testCharactersApis)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun returnCharactersIfGetCharactersSuccess() = runTest {
        val expected = ItemsResponse(
            info = tInfo,
            results = characters
        )

        setSuccessWebserverResponse(expected)

        val actualResponse =
            charactersRepository.getCharacters(1) as NetworkResult.Success
        assertEquals(NetworkResult.Success(expected), actualResponse)
    }

    @Test
    fun returnErrorIfGetCharactersFailed() = runTest {
        setFailureWebserverResponse(tExpectedError)

        val actualResponse =
            charactersRepository.getCharacters(1) as NetworkResult.Failure
        assertEquals(tExpectedError.errorCode, actualResponse.errorCode)
        assertEquals(tExpectedError.isNetworkError, actualResponse.isNetworkError)
    }

    @Test
    fun returnCharacterIfGetCharacterDetailsSuccess() = runTest {
        val expected = tCharacter

        setSuccessWebserverResponse(expected)

        val actualResponse =
            charactersRepository.getCharacterDetails(1) as NetworkResult.Success
        assertEquals(NetworkResult.Success(expected), actualResponse)
    }

    @Test
    fun returnErrorIfGetCharacterDetailsFailed() = runTest {
        setFailureWebserverResponse(tExpectedError)

        val actualResponse =
            charactersRepository.getCharacterDetails(1) as NetworkResult.Failure
        assertEquals(tExpectedError.errorCode, actualResponse.errorCode)
        assertEquals(tExpectedError.isNetworkError, actualResponse.isNetworkError)
    }

    private fun setSuccessWebserverResponse(expected: Any) {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(expected))
        mockWebServer.enqueue(response)
    }

    private fun setFailureWebserverResponse(expected: Any) {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(Gson().toJson(expected))
        mockWebServer.enqueue(response)
    }
}