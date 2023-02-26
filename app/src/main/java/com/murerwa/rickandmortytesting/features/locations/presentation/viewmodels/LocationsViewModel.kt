package com.murerwa.rickandmortytesting.features.locations.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.core.network.convertToUIState
import com.murerwa.rickandmortytesting.features.locations.domain.model.Location
import com.murerwa.rickandmortytesting.core.models.ItemsResponse
import com.murerwa.rickandmortytesting.features.locations.domain.repository.LocationsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val app: Application,
    private val repository: LocationsRepository
): ViewModel() {
    val locationsResponse = MutableLiveData<UIState<ItemsResponse<Location>>>(UIState.Loading)

    fun getLocations() = viewModelScope.launch {
        locationsResponse.value = UIState.Loading

        val uiState = convertToUIState(
            response = repository.getLocations(page = 1),
            app = app,
            errorMessage = app.getString(R.string.network_error_no_items_found)
        )

        locationsResponse.value = uiState
    }
}