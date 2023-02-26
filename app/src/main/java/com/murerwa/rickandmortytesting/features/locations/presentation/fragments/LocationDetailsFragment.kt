package com.murerwa.rickandmortytesting.features.locations.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.databinding.FragmentLocationDetailsBinding

class LocationDetailsFragment : Fragment(R.layout.fragment_location_details) {
    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLocationDetailsBinding.bind(view)
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}