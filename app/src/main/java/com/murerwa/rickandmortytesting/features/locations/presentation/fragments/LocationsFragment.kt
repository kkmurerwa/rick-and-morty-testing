package com.murerwa.rickandmortytesting.features.locations.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.databinding.FragmentLocationsBinding
import com.murerwa.rickandmortytesting.features.locations.presentation.viewmodels.LocationsViewModel
import com.murerwa.rickandmortytesting.features.locations.presentation.adapters.LocationsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationsFragment : Fragment(R.layout.fragment_locations) {
    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: LocationsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLocationsBinding.bind(view)

        val toolbar = binding.toolbar.root
        toolbar.title = "Locations"

        fetchLocations()
    }

    private fun fetchLocations() {
        viewModel.getLocations()

        viewModel.locationsResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is UIState.Loading -> {}
                is UIState.Success -> {
                    val locations = response.value.results

                    binding.recyclerLocations.adapter = LocationsAdapter(
                        locations = locations,
                        onClickListener = {
                            findNavController().navigate(
                                LocationsFragmentDirections.locationsToLocationDetails(it.id)
                            )
                        }
                    )
                }
                is UIState.Error -> {

                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}