package com.murerwa.rickandmortytesting.presentation.characters

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.domain.models.common.ItemsResponse
import com.murerwa.rickandmortytesting.data.network.NetworkResult
import com.murerwa.rickandmortytesting.data.network.UIState
import com.murerwa.rickandmortytesting.data.network.convertToUIState
import com.murerwa.rickandmortytesting.domain.models.characters.Character
import com.murerwa.rickandmortytesting.domain.repositories.CharactersRepository
import com.murerwa.rickandmortytesting.utils.readError
import kotlinx.coroutines.launch
import timber.log.Timber
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