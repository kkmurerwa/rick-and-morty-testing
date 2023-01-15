package com.murerwa.rickandmortytesting.presentation.locations

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.data.network.UIState
import com.murerwa.rickandmortytesting.data.network.convertToUIState
import com.murerwa.rickandmortytesting.domain.models.characters.Location
import com.murerwa.rickandmortytesting.domain.models.common.ItemsResponse
import com.murerwa.rickandmortytesting.domain.repositories.LocationsRepository
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val app: Application,
    private val repository: LocationsRepository
): ViewModel() {
    val locationsResponse = MutableLiveData<UIState<ItemsResponse<Location>>>(UIState.Loading)

    fun getEpisodes() = viewModelScope.launch {
        locationsResponse.value = UIState.Loading

        val uiState = convertToUIState(
            response = repository.getLocations(page = 1),
            app = app,
            errorMessage = app.getString(R.string.network_error_no_items_found)
        )

        locationsResponse.value = uiState
    }
}