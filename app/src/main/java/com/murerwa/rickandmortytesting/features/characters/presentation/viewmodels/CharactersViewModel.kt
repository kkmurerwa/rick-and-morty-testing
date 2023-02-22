package com.murerwa.rickandmortytesting.features.characters.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.core.network.convertToUIState
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.features.characters.domain.repositories.CharactersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val app: Application,
    private val repository: CharactersRepository
) : ViewModel() {
    val charactersResponse = MutableLiveData<UIState<ItemsResponse<Character>>>(UIState.Loading)

    fun getCharacters() = viewModelScope.launch {
        charactersResponse.value = UIState.Loading

        val uiState = convertToUIState(
            response = repository.getCharacters(page = 1),
            app = app,
            errorMessage = app.getString(R.string.network_error_no_items_found)
        )

        charactersResponse.value = uiState
    }

}