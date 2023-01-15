package com.murerwa.rickandmortytesting.presentation.episodes

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.data.network.UIState
import com.murerwa.rickandmortytesting.data.network.convertToUIState
import com.murerwa.rickandmortytesting.domain.models.common.ItemsResponse
import com.murerwa.rickandmortytesting.domain.models.episodes.Episode
import com.murerwa.rickandmortytesting.domain.repositories.EpisodesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val app: Application,
    private val repository: EpisodesRepository
): ViewModel() {
    val episodesResponse = MutableLiveData<UIState<ItemsResponse<Episode>>>(UIState.Loading)

    fun getEpisodes() = viewModelScope.launch {
        episodesResponse.value = UIState.Loading

        val uiState = convertToUIState(
            response = repository.getEpisodes(page = 1),
            app = app,
            errorMessage = app.getString(R.string.network_error_no_items_found)
        )

        episodesResponse.value = uiState
    }
}