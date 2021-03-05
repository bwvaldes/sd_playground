package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.data.model.ExploreFilter
import com.scubadeving.sd_playground.databinding.ItemExploreFilterCardBinding

class ExploreFilterAdapter : ListAdapter<ExploreFilter, RecyclerView.ViewHolder>(ExploreFilterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreFilterViewHolder {
        return ExploreFilterViewHolder(
            ItemExploreFilterCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val exploreFilter = getItem(position)
        (holder as ExploreFilterViewHolder).bind(exploreFilter)
    }

    inner class ExploreFilterViewHolder(private val binding: ItemExploreFilterCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(filter: ExploreFilter) {
            binding.apply {
                exploreFilterCardText.text = filter.name
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Explore Filter Card!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToExploreSitesDetail(it, filter)
                }
            }
        }

        private fun navigateToExploreSitesDetail(view: View, filter: ExploreFilter) {
            val directions = MainNavigationDirections.actionGlobalExploreDetailsFilteredFragment(filter.name, filter.isWildlife)
            view.findNavController().navigate(directions)
        }
    }

    private class ExploreFilterDiffCallback : DiffUtil.ItemCallback<ExploreFilter>() {

        override fun areItemsTheSame(oldItem: ExploreFilter, newItem: ExploreFilter): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ExploreFilter, newItem: ExploreFilter): Boolean {
            return oldItem == newItem
        }
    }
}
