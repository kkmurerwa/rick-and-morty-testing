package com.murerwa.rickandmortytesting.core.network

import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseRepositoryTest: BaseUnitTest() {
    private val baseRepository = BaseRepository()

    @Test
    fun `should return NetworkResult Success when api call is successful`() = runTest {
        val result = baseRepository.safeApiCall { "Success" }
        assertTrue(result is NetworkResult.Success)
    }

    @Test
    fun `should return NetworkResult Failure when api call is unsuccessful`() = runTest {
        val result = baseRepository.safeApiCall { throw Exception("Error") }
        assertTrue(result is NetworkResult.Failure)
    }
}