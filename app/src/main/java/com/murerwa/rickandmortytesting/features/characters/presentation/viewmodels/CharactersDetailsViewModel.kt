package com.murerwa.rickandmortytesting.features.characters.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.core.network.convertToUIState
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.features.characters.domain.repositories.CharactersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersDetailsViewModel @Inject constructor(
    private val app: Application,
    private val repository: CharactersRepository
) : ViewModel() {
    val characterDetailsResponse = MutableLiveData<UIState<Character>>(UIState.Loading)

    fun getCharacterDetails(characterId: Int) = viewModelScope.launch {
        characterDetailsResponse.value = UIState.Loading

        val uiState = convertToUIState(
            response = repository.getCharacterDetails(characterId = characterId),
            app = app,
            errorMessage = app.getString(R.string.network_error_no_items_found)
        )

        characterDetailsResponse.value = uiState
    }

}