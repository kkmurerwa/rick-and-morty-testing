package com.murerwa.rickandmortytesting.presentation.episodes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.databinding.FragmentEpisodesBinding
import javax.inject.Inject

class EpisodesFragment : Fragment(R.layout.fragment_episodes) {
    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: EpisodesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentEpisodesBinding.bind(view)

        val toolbar = binding.toolbar.root
        toolbar.title = "Episodes"
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}