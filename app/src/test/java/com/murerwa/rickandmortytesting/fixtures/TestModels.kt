package com.murerwa.rickandmortytesting.fixtures

import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.core.models.Info

val tCharactersResponse = ItemsResponse<Character>(
    info = Info(
        count = 671,
        pages = 34,
        next = "https://rickandmortyapi.com/api/character/?page=2",
        prev = ""
    ),
    results = listOf()
)