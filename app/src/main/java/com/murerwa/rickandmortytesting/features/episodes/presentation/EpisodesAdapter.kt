package com.murerwa.rickandmortytesting.features.episodes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murerwa.rickandmortytesting.databinding.ListItemEpisodeBinding
import com.murerwa.rickandmortytesting.features.episodes.domain.model.Episode

class EpisodesAdapter(
    private val episodes: List<Episode>,
    private val onClickListener: (Episode) -> Unit
): RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = episodes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEpisode = episodes[position]
        holder.bindView(currentEpisode)
    }


    inner class ViewHolder(private val binding: ListItemEpisodeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(episode: Episode) {
            binding.apply {
                textEpisodeCode.text = episode.episode
                textEpisodeTitle.text = episode.name
                textEpisodeAirDate.text = episode.air_date
            }
        }
    }

}