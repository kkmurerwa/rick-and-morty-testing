package com.murerwa.rickandmortytesting.domain.repositories

import com.murerwa.rickandmortytesting.domain.network.NetworkResult
import com.murerwa.rickandmortytesting.domain.models.characters.CharacterItem
import com.murerwa.rickandmortytesting.domain.models.characters.CharactersResponse

interface CharactersRepository {

    suspend fun getCharacters(page: Int): NetworkResult<CharactersResponse>

    suspend fun getCharacterDetails(characterId: Int): NetworkResult<CharacterItem>

}