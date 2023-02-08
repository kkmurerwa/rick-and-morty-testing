package com.murerwa.rickandmortytesting.features.locations.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murerwa.rickandmortytesting.databinding.ListItemLocationBinding
import com.murerwa.rickandmortytesting.features.locations.domain.model.Location

class LocationsAdapter(
    private val locations: List<Location>,
    private val onClickListener: (Location) -> Unit
): RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = locations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentLocation = locations[position]
        holder.bindView(currentLocation)
    }

    inner class ViewHolder(private val binding: ListItemLocationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(location: Location) {
            binding.textLocationName.text = location.name
            binding.textLocationType.text = location.type
            binding.textLocationDimension.text = location.dimension
            binding.root.setOnClickListener {
                onClickListener(location)
            }
        }
    }
}