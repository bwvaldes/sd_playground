package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.data.model.gear.Gear
import com.scubadeving.sd_playground.databinding.ItemGearCardBinding
import com.scubadeving.sd_playground.ui.main.dashboard.profile.ProfileFragmentDirections

class GearAdapter : ListAdapter<Gear, RecyclerView.ViewHolder>(GearDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GearViewHolder {
        return GearViewHolder(
            ItemGearCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gear = getItem(position)
        (holder as GearViewHolder).bind(gear)
    }

    inner class GearViewHolder(private val binding: ItemGearCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gear: Gear) {
            binding.apply {
                gearCardItemText.text = gear.name
                setClickListener {
                    navigateToGearDetail(it, gear)
                }
            }
        }

        private fun navigateToGearDetail(view: View, gear: Gear) {
            val directions =
                ProfileFragmentDirections.actionProfileFragmentToGearProfileDetailFragment(
                    gear.name!!
                )
            view.findNavController().navigate(directions)
        }
    }

    private class GearDiffCallback : DiffUtil.ItemCallback<Gear>() {

        override fun areItemsTheSame(oldItem: Gear, newItem: Gear): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Gear, newItem: Gear): Boolean {
            return oldItem == newItem
        }
    }
}
