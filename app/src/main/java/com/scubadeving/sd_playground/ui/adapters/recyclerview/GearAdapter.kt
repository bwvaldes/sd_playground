package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.GearProfile
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_gear_card.view.*
import kotlinx.android.synthetic.main.item_gear_profile_card.view.*

class GearAdapter(private val gearProfiles: ArrayList<GearProfile>, val orientation: Boolean) :
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
        holder.bind(gearProfile, position)
    }

    inner class GearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(gearProfile: GearProfile, position: Int) {
            itemView.apply {
                if (orientation) {
                    configureGearProfileLayout(gearProfile)
                } else {
                    configureGearProfileItemLayout(gearProfile)
                }
                setOnClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(itemView.context, "Just Clicked Gear Profile Item!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        private fun View.configureGearProfileLayout(gearProfile: GearProfile) {
            gear_profile_card_text.text = gearProfile.name
        }

        private fun View.configureGearProfileItemLayout(gearProfile: GearProfile) {
            gear_card_item_text.text = gearProfile.gearList.first().name
        }
    }
}