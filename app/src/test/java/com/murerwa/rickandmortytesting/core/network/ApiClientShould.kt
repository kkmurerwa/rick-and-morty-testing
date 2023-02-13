package com.murerwa.rickandmortytesting.core.network

import com.google.gson.Gson
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.features.characters.data.repository.CharactersRepositoryImpl
import com.murerwa.rickandmortytesting.features.episodes.data.repository.EpisodesRepositoryImpl
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
class ApiClientShould: BaseUnitTest() {
    private val characters = listOf(tCharacter)
    private val episodes = listOf(tEpisode)
    private val locations = listOf(tLocation)

    private lateinit var testApis: ApiClient
    private lateinit var mockWebServer: MockWebServer
    private lateinit var charactersRepository: CharactersRepositoryImpl
    private lateinit var episodesRepository: EpisodesRepositoryImpl
    private lateinit var locationsRepository: LocationsRepositoryImpl

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        testApis = RetrofitHelper.testApiInstance(mockWebServer.url("/").toString())
        charactersRepository = CharactersRepositoryImpl(testApis)
        episodesRepository = EpisodesRepositoryImpl(testApis)
        locationsRepository = LocationsRepositoryImpl(testApis)
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

    @Test
    fun returnEpisodesIfGetEpisodesSuccess() = runTest {
        val expected = ItemsResponse(
            info = tInfo,
            results = episodes
        )

        setSuccessWebserverResponse(expected)

        val actualResponse =
            episodesRepository.getEpisodes(1) as NetworkResult.Success
        assertEquals(NetworkResult.Success(expected), actualResponse)
    }

    @Test
    fun returnErrorIfGetEpisodesFailed() = runTest {
        setFailureWebserverResponse(tExpectedError)

        val actualResponse =
            episodesRepository.getEpisodes(1) as NetworkResult.Failure
        assertEquals(tExpectedError.errorCode, actualResponse.errorCode)
        assertEquals(tExpectedError.isNetworkError, actualResponse.isNetworkError)
    }

    @Test
    fun returnLocationsIfGetLocationsSuccess() = runTest {
        val expected = ItemsResponse(
            info = tInfo,
            results = locations
        )

        setSuccessWebserverResponse(expected)

        val actualResponse =
            locationsRepository.getLocations(1) as NetworkResult.Success
        assertEquals(NetworkResult.Success(expected), actualResponse)
    }

    @Test
    fun returnErrorIfGetLocationsFailed() = runTest {
        setFailureWebserverResponse(tExpectedError)

        val actualResponse =
            locationsRepository.getLocations(1) as NetworkResult.Failure
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