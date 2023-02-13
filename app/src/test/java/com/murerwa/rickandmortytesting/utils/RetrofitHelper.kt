package com.murerwa.rickandmortytesting.utils

import com.murerwa.rickandmortytesting.core.network.ApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    /**
     * as dependency injection is not used in this project for brevity,
     * static object initialization is done for demo purpose.
     *
     * @param url: Base url for apis
     * @return TestApis: Instance of the test api retrofit interface
     **/
    fun testApiInstance(url: String): ApiClient {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }
}