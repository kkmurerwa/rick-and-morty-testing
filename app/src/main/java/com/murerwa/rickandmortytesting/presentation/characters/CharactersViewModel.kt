package com.murerwa.rickandmortytesting.presentation.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.rickandmortytesting.domain.models.common.ItemsResponse
import com.murerwa.rickandmortytesting.data.network.NetworkResult
import com.murerwa.rickandmortytesting.data.network.UIState
import com.murerwa.rickandmortytesting.domain.models.characters.Character
import com.murerwa.rickandmortytesting.domain.repositories.CharactersRepository
import com.murerwa.rickandmortytesting.utils.readError
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
) : ViewModel() {
    val charactersResponse = MutableLiveData<UIState<ItemsResponse<Character>>>(UIState.Loading)

    fun getCharacters() {
        viewModelScope.launch {
            charactersResponse.value = UIState.Loading

            when (val response = repository.getCharacters(page = 1)) {
                is NetworkResult.Success -> {
                    charactersResponse.value = UIState.Success(response.value)
                }
                is NetworkResult.Failure -> {
                    if (response.isNetworkError) {
                        charactersResponse.value = UIState.Error("Network error")
                    } else {
                        if (response.errorBody != null) {
                            Timber.d("Response is Not Null")
                            val error = response.errorBody.readError()
                            if (!error.isNullOrEmpty()) {
                                charactersResponse.value = UIState.Error(error)
                            } else {
                                charactersResponse.value = UIState.Error("Error fetching characters")
                            }

                        } else {
                            Timber.d("Response is Null")
                            charactersResponse.value = UIState.Error("Error fetching characters")
                        }
                    }
                }
            }
        }
    }

}