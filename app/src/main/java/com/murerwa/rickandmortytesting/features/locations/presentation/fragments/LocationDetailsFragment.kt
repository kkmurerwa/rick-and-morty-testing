package com.murerwa.rickandmortytesting.features.locations.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.databinding.FragmentLocationDetailsBinding
import com.murerwa.rickandmortytesting.features.characters.presentation.fragments.CharacterDetailsFragmentArgs
import com.murerwa.rickandmortytesting.features.characters.presentation.viewmodels.CharactersDetailsViewModel
import com.murerwa.rickandmortytesting.features.locations.domain.model.Location
import com.murerwa.rickandmortytesting.features.locations.presentation.viewmodels.LocationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LocationDetailsFragment : Fragment(R.layout.fragment_location_details) {
    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: LocationsViewModel

    private val args by navArgs<LocationDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLocationDetailsBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        val toolbar = binding.toolbar.root
        toolbar.title = "Location Details"
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        fetchLocationDetails()
    }

    private fun fetchLocationDetails() {
        viewModel.getLocationDetails(args.locationId)

        viewModel.locationDetailsResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is UIState.Loading -> {}
                is UIState.Success -> {
                    val location  = response.value

                    Timber.d("Character: $location")

                    setLocationDetails(location)
                }
                is UIState.Error -> {

                }
            }
        }
    }

    private fun setLocationDetails(location: Location) {
        binding.apply {
            textViewLocationName.text = location.name
            textViewLocationType.text = location.type
            textViewLocationDimension.text = location.dimension
            textViewLocationResidents.text = "${location.residents.size} residents"
            textViewLocationCreated.text = location.created
        }
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}