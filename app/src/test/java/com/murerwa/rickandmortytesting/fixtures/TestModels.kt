package com.murerwa.rickandmortytesting.fixtures

import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.core.models.Info
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.features.characters.domain.model.CharacterLocation
import com.murerwa.rickandmortytesting.features.characters.domain.model.Origin
import com.murerwa.rickandmortytesting.features.episodes.domain.model.Episode
import okhttp3.ResponseBody
import org.json.JSONObject

val tResponseBody: ResponseBody = ResponseBody.create(null, "error message")

val tInfo = Info(
    count = 1,
    next = "",
    pages = 1,
    prev = ""
)

val tCharacter = Character(
    created = "created",
    episode = listOf(""),
    gender = "",
    id = 1,
    image = "",
    location = CharacterLocation(
        name = "",
        url = ""
    ),
    name = "",
    origin = Origin(
        name = "",
        url = ""
    ),
    species = "",
    status = "",
    type = "",
    url = ""
)

val tEpisode = Episode(
    air_date = "",
    characters = listOf(""),
    created = "",
    episode = "",
    id = 1,
    name = "",
    url = ""
)

val tLocation = com.murerwa.rickandmortytesting.features.locations.domain.model.Location(
    created = "",
    dimension = "",
    id = 1,
    name = "",
    residents = listOf(""),
    type = "",
    url = ""
)

val tExpectedError = NetworkResult.Failure(
    isNetworkError = false,
    errorCode = 400,
    errorBody = null
)