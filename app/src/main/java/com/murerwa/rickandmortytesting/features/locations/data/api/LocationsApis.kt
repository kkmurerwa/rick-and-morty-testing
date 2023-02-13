package com.murerwa.rickandmortytesting.features.locations.data.api

import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.Urls
import com.murerwa.rickandmortytesting.features.locations.domain.model.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsApis {
    @GET(Urls.LOCATIONS)
    suspend fun getLocations(
        @Query("page") page: Int,
    ): ItemsResponse<Location>
}