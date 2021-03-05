package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.data.model.gear.GearProfile
import com.scubadeving.sd_playground.databinding.ItemGearProfileCardBinding
import com.scubadeving.sd_playground.ui.main.dashboard.profile.ProfileFragmentDirections

class GearProfileAdapter : ListAdapter<GearProfile, RecyclerView.ViewHolder>(GearProfileDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GearProfileViewHolder {
        return GearProfileViewHolder(
            ItemGearProfileCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gearProfile = getItem(position)
        (holder as GearProfileViewHolder).bind(gearProfile)
    }

    inner class GearProfileViewHolder(private val binding: ItemGearProfileCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gearProfile: GearProfile) {
            binding.apply {
                gearProfileCardText.text = gearProfile.name
                setClickListener {
                    navigateToGearProfileDetail(it, gearProfile)
                }
            }
        }

        private fun navigateToGearProfileDetail(view: View, gearProfile: GearProfile) {
            val directions =
                ProfileFragmentDirections.actionProfileFragmentToGearProfileDetailFragment(
                    gearProfile.name!!
                )
            view.findNavController().navigate(directions)
        }
    }

    private class GearProfileDiffCallback : DiffUtil.ItemCallback<GearProfile>() {

        override fun areItemsTheSame(oldItem: GearProfile, newItem: GearProfile): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: GearProfile, newItem: GearProfile): Boolean {
            return oldItem == newItem
        }
    }
}
