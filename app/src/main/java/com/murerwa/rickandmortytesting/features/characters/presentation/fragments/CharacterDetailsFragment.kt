package com.murerwa.rickandmortytesting.features.characters.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.core.network.UIState
import com.murerwa.rickandmortytesting.databinding.FragmentCharacterDetailsBinding
import com.murerwa.rickandmortytesting.features.characters.domain.model.Character
import com.murerwa.rickandmortytesting.features.characters.presentation.viewmodels.CharactersDetailsViewModel
import com.murerwa.rickandmortytesting.features.characters.presentation.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {
    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: CharactersDetailsViewModel

    private val args by navArgs<CharacterDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCharacterDetailsBinding.bind(view)

        val toolbar = binding.toolbar.root
//        toolbar.title = "Character Details"
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        fetchCharacterDetails()
    }

    private fun fetchCharacterDetails() {
        viewModel.getCharacterDetails(args.characterId)

        viewModel.characterDetailsResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is UIState.Loading -> {}
                is UIState.Success -> {
                    val character  = response.value

                    Timber.d("Character: $character")

                    setCharacterDetails(character)
                }
                is UIState.Error -> {

                }
            }
        }
    }

    private fun setCharacterDetails(character: Character) {
        binding.apply {
            textViewCharacterName.text = character.name
            textViewCharacterStatus.text = character.status
            textViewCharacterSpecies.text = character.species
            textViewCharacterEpisodes.text = character.episode.size.toString()
            textViewCharacterGender.text = character.gender
            textViewCharacterOrigin.text = character.origin.name
            textViewCharacterLocation.text = character.location.name
            textViewCharacterCreatedDate.text = character.created

            Glide.with(this@CharacterDetailsFragment)
                .load(character.image)
                .into(imageViewCharacter)
        }
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}