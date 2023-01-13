package com.murerwa.rickandmortytesting.presentation.characters

import android.os.Bundle
import android.provider.Contacts.Intents.UI
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.murerwa.rickandmortytesting.R
import com.murerwa.rickandmortytesting.databinding.FragmentCharactersBinding
import com.murerwa.rickandmortytesting.domain.models.characters.CharacterItem
import com.murerwa.rickandmortytesting.domain.network.UIState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: CharactersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCharactersBinding.bind(view)

        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModel.getCharacters()

        viewModel.charactersResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is UIState.Loading -> {}
                is UIState.Success -> {
                    val characters  = response.value.results

                    Timber.d("Characters: $characters")

                    binding.recyclerCharacters.adapter = CharactersAdapter(
                        characters = characters,
                        onClickListener = { character ->
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