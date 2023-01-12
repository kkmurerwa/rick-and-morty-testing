package com.murerwa.rickandmortytesting.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murerwa.rickandmortytesting.databinding.ListItemCharacterBinding
import com.murerwa.rickandmortytesting.domain.models.characters.CharacterItem

class CharactersAdapter(
    private val characters: List<CharacterItem>,
    private val onClickListener: (CharacterItem) -> Unit
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

    inner class ViewHolder(binding: ListItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(character: CharacterItem) {

        }
    }
}