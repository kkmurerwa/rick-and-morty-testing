package com.murerwa.rickandmortytesting.presentation.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.databinding.FragmentCharactersBinding
import com.murerwa.rickandmortytesting.domain.models.characters.CharacterItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCharactersBinding.bind(view)

        setUpViews()
    }

    private fun setUpViews() {
        binding.apply {
            recyclerCharacters.adapter = CharactersAdapter(
                characters = listOf(),
                onClickListener = { character ->
                    // Navigate to details fragment
                }
            )
        }
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}