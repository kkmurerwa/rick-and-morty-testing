package com.murerwa.rickandmortytesting.core.utils

import com.murerwa.rickandmortytesting.fixtures.tResponseBody
import com.murerwa.rickandmortytesting.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NetworkExtensionsShould: BaseUnitTest() {
    private val jsonObject = mock<JSONObject>()

    @Test
    fun readErrorFromResponseBody() {
        val responseBody = tResponseBody

        assertEquals("unexpected error", responseBody.readError())
    }

    @Test
    fun capitalizeString() {
        val string = "hello world"

        assertEquals("Hello world", string.capitalizeString())
    }
}