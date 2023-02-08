package com.murerwa.rickandmortytesting.features.characters.presentation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murerwa.rickandmortytesting.databinding.ListItemCharacterBinding
import com.murerwa.rickandmortytesting.features.characters.domain.models.Character
import com.murerwa.rickandmortytesting.core.utils.capitalizeString
import com.murerwa.rickandmortytesting.core.utils.loadImage

class CharactersAdapter(
    private val characters: List<Character>,
    private val onClickListener: (Character) -> Unit
): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = characters[position]

        holder.bindView(currentItem)
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(private val binding: ListItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(character: Character) {
            binding.apply {
                imageView.loadImage(character.image)

                textCharacterName.text = character.name
                textCharacterLivingStatus.text = character.status.capitalizeString()
                textCharacterSpecies.text = character.species

                val color = when (character.status) {
                    "Alive" -> Color.GREEN
                    "unknown" -> Color.MAGENTA
                    else -> Color.RED
                }

                textCharacterLivingStatus.setTextColor(color)
            }
        }
    }
}