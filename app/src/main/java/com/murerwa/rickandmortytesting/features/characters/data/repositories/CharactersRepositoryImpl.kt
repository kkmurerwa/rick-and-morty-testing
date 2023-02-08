package com.murerwa.rickandmortytesting.features.characters.data.repositories

import com.murerwa.rickandmortytesting.core.network.ApiClient
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.BaseRepository
import com.murerwa.rickandmortytesting.core.network.NetworkResult
import com.murerwa.rickandmortytesting.features.characters.domain.repositories.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient
): CharactersRepository, BaseRepository() {
    override suspend fun getCharacters(page: Int): NetworkResult<ItemsResponse<Character>> {
        return safeApiCall { apiClient.getCharacters(page) }
    }

    override suspend fun getCharacterDetails(characterId: Int): NetworkResult<Character> {
        return safeApiCall { apiClient.getCharacterDetails(characterId) }
    }

}