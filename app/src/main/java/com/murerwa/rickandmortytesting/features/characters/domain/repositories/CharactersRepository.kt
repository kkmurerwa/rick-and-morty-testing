package com.murerwa.rickandmortytesting.features.characters.domain.repositories

import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.features.characters.domain.models.Character
import com.murerwa.rickandmortytesting.core.models.ItemsResponse

interface CharactersRepository {

    suspend fun getCharacters(page: Int): NetworkResult<ItemsResponse<Character>>

    suspend fun getCharacterDetails(characterId: Int): NetworkResult<Character>

}