package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.gear.GearProfile
import com.scubadeving.sd_playground.ui.main.dashboard.profile.ProfileFragmentDirections
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_gear_profile_card.view.gear_profile_card_text

class GearAdapter(private val gearProfiles: List<GearProfile>, val orientation: Boolean) :
    RecyclerView.Adapter<GearAdapter.GearViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GearViewHolder {
        val inflatedView = if (orientation) {
            parent.inflate(R.layout.item_gear_profile_card, false)
        } else {
            parent.inflate(R.layout.item_gear_card, false)
        }
        return GearViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = gearProfiles.size

    override fun onBindViewHolder(holder: GearViewHolder, position: Int) {
        val gearProfile = gearProfiles[position]
        holder.bind(gearProfile)
    }

    inner class GearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(gearProfile: GearProfile) {
            itemView.apply {
                if (orientation) {
                    configureGearProfileLayout(gearProfile)
                } else {
                    configureGearProfileItemLayout(gearProfile)
                }
            }
        }

        private fun View.configureGearProfileLayout(gearProfile: GearProfile) {
            gear_profile_card_text.text = gearProfile.name
            setOnClickListener {
                navigateToGearProfileDetail(it, gearProfile)
            }
        }

        private fun navigateToGearProfileDetail(view: View, gearProfile: GearProfile) {
            val directions = ProfileFragmentDirections.actionProfileFragmentToGearProfileDetailFragment(gearProfile.name!!)
            view.findNavController().navigate(directions)
        }

        private fun View.configureGearProfileItemLayout(gearProfile: GearProfile) {
//            gear_card_item_text.text = gearProfile.gearList?.first()?.name!!
        }
    }
}
