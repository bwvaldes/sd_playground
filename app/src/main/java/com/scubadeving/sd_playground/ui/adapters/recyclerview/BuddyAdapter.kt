package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Diver
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_explore_buddies_horizontal_card.view.*
import kotlinx.android.synthetic.main.item_explore_buddies_vertical_card.view.*

class BuddyAdapter(private val divers: List<Diver>, val orientation: Boolean) :
    RecyclerView.Adapter<BuddyAdapter.BuddyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuddyViewHolder {
        val inflatedView = if (orientation) {
            parent.inflate(R.layout.item_explore_buddies_horizontal_card, false)
        } else {
            parent.inflate(R.layout.item_explore_buddies_vertical_card, false)

        }
        return BuddyViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = divers.size

    override fun onBindViewHolder(holder: BuddyViewHolder, position: Int) {
        val details = divers[position]
        holder.bind(details, position)
    }

    inner class BuddyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(
                itemView.context,
                "Just Clicked Buddy Card!",
                Toast.LENGTH_SHORT
            )
                .show()
            // Todo: Should navigate to specific user and react whether it is current user
            view.findNavController().navigate(R.id.profileFragment)
        }

        fun bind(diver: Diver, position: Int) {
            itemView.apply {
                if (orientation) {
                    diver_card_horizontal_avatar.setImageResource(R.drawable.ic_profile)
                    diver_card_horizontal_background.setImageResource(R.color.purple_200)
                    diver_card_horizontal_name.text = diver.name
                    diver_card_horizontal_level.text = diver.certLevel
                    if (diver.buddyCount != 1) {
                        diver_card_horizontal_buddy_count.text =
                            diver.buddyCount.toString().plus(" Buddies")
                    } else {
                        diver_card_horizontal_buddy_count.text =
                            diver.buddyCount.toString().plus(" Buddy")
                    }
                } else {
                    diver_card_vertical_avatar.setImageResource(R.drawable.ic_profile)
                    diver_card_vertical_background.setImageResource(R.color.teel_200)
                    diver_card_vertical_name.text = diver.name
                    diver_card_vertical_level.text = diver.certLevel
                }
            }
        }

    }
}