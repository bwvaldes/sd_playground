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
import com.scubadeving.sd_playground.data.model.wildlife.Wildlife
import com.scubadeving.sd_playground.databinding.ItemWildlifeCardSmallBinding
import com.scubadeving.sd_playground.ui.details.divesite.DiveSiteDetailFragment
import com.scubadeving.sd_playground.ui.details.wildlife.detail.WildlifeDetailFragment
import com.scubadeving.sd_playground.ui.main.explore.filtered.ExploreDetailsFilteredFragment
import com.scubadeving.sd_playground.ui.main.explore.wildlife.ExploreWildlifeFragment
import com.scubadeving.sd_playground.ui.main.logbook.entry.DiveLogEntryFragment
import com.scubadeving.sd_playground.ui.main.logbook.wildlife.LogbookWildlifeFragment

/**
 * Adapter for the [RecyclerView] in:
 * - [WildlifeDetailFragment]
 * - [DiveSiteDetailFragment]
 * - [ExploreWildlifeFragment]
 * - [ExploreDetailsFilteredFragment]
 * - [LogbookWildlifeFragment]
 * - [DiveLogEntryFragment]
 */
class WildlifeAdapter : ListAdapter<Wildlife, RecyclerView.ViewHolder>(WildlifeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WildlifeViewHolder {
        return WildlifeViewHolder(
            ItemWildlifeCardSmallBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val wildlife = getItem(position)
        (holder as WildlifeViewHolder).bind(wildlife)
    }

    inner class WildlifeViewHolder(private val binding: ItemWildlifeCardSmallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Wildlife) {
            binding.apply {
                wildlife = item
                executePendingBindings()
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Wildlife Item!",
                        Toast.LENGTH_SHORT
                    ).show()
                    wildlife?.let { wildlife ->
                        navigateToWildlifeDetail(it, wildlife)
                    }
                }
            }
        }

        private fun navigateToWildlifeDetail(view: View, wildlife: Wildlife) {
            val directions =
                MainNavigationDirections.actionGlobalWildlifeDetailFragment(
                    wildlife.commonName ?: ""
                )
            view.findNavController().navigate(directions)
        }
    }

    private class WildlifeDiffCallback : DiffUtil.ItemCallback<Wildlife>() {

        override fun areItemsTheSame(oldItem: Wildlife, newItem: Wildlife): Boolean {
            return oldItem.commonName == newItem.commonName
        }

        override fun areContentsTheSame(oldItem: Wildlife, newItem: Wildlife): Boolean {
            return oldItem == newItem
        }
    }
}
