package com.murerwa.rickandmortytesting.data.repositories

import com.murerwa.rickandmortytesting.data.network.ApiClient
import com.murerwa.rickandmortytesting.domain.models.characters.Character
import com.murerwa.rickandmortytesting.domain.models.common.ItemsResponse
import com.murerwa.rickandmortytesting.data.network.BaseRepository
import com.murerwa.rickandmortytesting.data.network.NetworkResult
import com.murerwa.rickandmortytesting.domain.repositories.CharactersRepository
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