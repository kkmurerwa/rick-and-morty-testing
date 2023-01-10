package com.murerwa.rickandmortytesting.data.repositories

import com.murerwa.rickandmortytesting.domain.network.ApiClient
import com.murerwa.rickandmortytesting.domain.models.characters.CharacterItem
import com.murerwa.rickandmortytesting.domain.models.characters.CharactersResponse
import com.murerwa.rickandmortytesting.domain.network.BaseRepository
import com.murerwa.rickandmortytesting.domain.network.NetworkResult
import com.murerwa.rickandmortytesting.domain.repositories.CharactersRepository

class CharactersRepositoryImpl(
    private val apiClient: ApiClient
): CharactersRepository, BaseRepository() {
    override suspend fun getCharacters(page: Int): NetworkResult<CharactersResponse> {
        return safeApiCall { apiClient.getCharacters(page) }
    }

    override suspend fun getCharacterDetails(characterId: Int): NetworkResult<CharacterItem> {
        return safeApiCall { apiClient.getCharacterDetails(characterId) }
    }

}