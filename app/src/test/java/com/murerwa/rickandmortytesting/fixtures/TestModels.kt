package com.murerwa.rickandmortytesting.fixtures

import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.core.models.Info
import com.murerwa.rickandmortytesting.features.episodes.domain.model.Episode
import okhttp3.ResponseBody
import org.json.JSONObject

val tResponseBody: ResponseBody = ResponseBody.create(null, "error message")