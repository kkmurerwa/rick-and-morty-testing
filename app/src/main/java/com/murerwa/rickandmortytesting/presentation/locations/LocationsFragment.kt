package com.murerwa.rickandmortytesting.presentation.locations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.databinding.FragmentLocationsBinding

class LocationsFragment : Fragment(R.layout.fragment_locations) {
    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLocationsBinding.bind(view)
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}