package com.example.asteroidradarapp.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.asteroidradarapp.databinding.FragmentItemAsteroidBinding
import com.example.asteroidradarapp.domain.Asteroid


class AsteroidAdapter(private val onClickListener: OnClickListener): ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder(FragmentItemAsteroidBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroid = getItem(position)

        holder.itemView.setOnClickListener{
            onClickListener.onClick(asteroid)
        }

        holder.bind(asteroid)
    }

    class AsteroidViewHolder(private var binding: FragmentItemAsteroidBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(asteroid: Asteroid){
            binding.asteroid = asteroid
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid:Asteroid) = clickListener(asteroid)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }



}