package com.murerwa.rickandmortytesting.presentation.locations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.databinding.FragmentLocationsBinding
import com.murerwa.rickandmortytesting.presentation.characters.CharactersViewModel
import javax.inject.Inject

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
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}