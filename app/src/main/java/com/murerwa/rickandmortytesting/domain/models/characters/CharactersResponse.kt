package com.murerwa.rickandmortytesting.domain.models.characters

data class CharactersResponse(
    val info: Info,
    val results: List<CharacterItem>
)