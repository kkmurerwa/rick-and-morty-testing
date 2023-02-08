package com.murerwa.rickandmortytesting.features.episodes.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.databinding.FragmentEpisodesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
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

        fetchEpisodes()
    }

    private fun fetchEpisodes() {
        viewModel.getEpisodes()

        viewModel.episodesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is UIState.Loading -> {}
                is UIState.Success -> {
                    val episodes = response.value.results

                    binding.recyclerEpisodes.adapter = EpisodesAdapter(
                        episodes = episodes,
                        onClickListener = {
                            // Navigate to details fragment
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