package com.murerwa.rickandmortytesting.domain.repositories

import com.murerwa.rickandmortytesting.data.network.NetworkResult
import com.murerwa.rickandmortytesting.domain.models.characters.Character
import com.murerwa.rickandmortytesting.domain.models.common.ItemsResponse

interface CharactersRepository {

    suspend fun getCharacters(page: Int): NetworkResult<ItemsResponse<Character>>

    suspend fun getCharacterDetails(characterId: Int): NetworkResult<Character>

}