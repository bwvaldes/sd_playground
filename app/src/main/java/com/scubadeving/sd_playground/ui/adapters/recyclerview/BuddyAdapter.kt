package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_explore_buddy_card_horizontal.view.diver_card_horizontal_add_buddy
import kotlinx.android.synthetic.main.item_explore_buddy_card_horizontal.view.diver_card_horizontal_avatar
import kotlinx.android.synthetic.main.item_explore_buddy_card_horizontal.view.diver_card_horizontal_background
import kotlinx.android.synthetic.main.item_explore_buddy_card_horizontal.view.diver_card_horizontal_buddy_count
import kotlinx.android.synthetic.main.item_explore_buddy_card_horizontal.view.diver_card_horizontal_clear
import kotlinx.android.synthetic.main.item_explore_buddy_card_horizontal.view.diver_card_horizontal_level
import kotlinx.android.synthetic.main.item_explore_buddy_card_horizontal.view.diver_card_horizontal_name
import kotlinx.android.synthetic.main.item_explore_buddy_card_vertical.view.diver_card_vertical_add_buddy
import kotlinx.android.synthetic.main.item_explore_buddy_card_vertical.view.diver_card_vertical_avatar
import kotlinx.android.synthetic.main.item_explore_buddy_card_vertical.view.diver_card_vertical_background
import kotlinx.android.synthetic.main.item_explore_buddy_card_vertical.view.diver_card_vertical_clear
import kotlinx.android.synthetic.main.item_explore_buddy_card_vertical.view.diver_card_vertical_level
import kotlinx.android.synthetic.main.item_explore_buddy_card_vertical.view.diver_card_vertical_name

class BuddyAdapter(private val divers: ArrayList<Diver>, val orientation: Boolean) :
    RecyclerView.Adapter<BuddyAdapter.BuddyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuddyViewHolder {
        val inflatedView = if (orientation) {
            parent.inflate(R.layout.item_explore_buddy_card_horizontal, false)
        } else {
            parent.inflate(R.layout.item_explore_buddy_card_vertical, false)
        }
        return BuddyViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return if (!orientation && divers.size > BUDDY_VERTICAL_LIMIT) {
            BUDDY_VERTICAL_LIMIT
        } else {
            divers.size
        }
    }

    override fun onBindViewHolder(holder: BuddyViewHolder, position: Int) {
        val diver = divers[position]
        holder.bind(diver, position)
    }

    inner class BuddyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(diver: Diver, position: Int) {
            itemView.apply {
                if (orientation) {
                    configureHorizontalBuddyLayout(position, diver)
                } else {
                    configureVerticalBuddyLayout(position, diver)
                }
                setOnClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Buddy Card!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToProfile(it, diver)
                }
            }
        }

        private fun View.configureHorizontalBuddyLayout(position: Int, diver: Diver) {
            diver_card_horizontal_clear.setOnClickListener { dismissBuddy(position) }
            diver_card_horizontal_avatar.setImageResource(R.drawable.ic_menu_avatar)
            diver_card_horizontal_background.setImageResource(R.color.purple_200)
            diver_card_horizontal_name.text = diver.firstName
            diver_card_horizontal_level.text = diver.certifications?.first()?.certificationName
            configureBuddyCountText(diver)
            diver_card_horizontal_add_buddy.setOnClickListener {
                Toast.makeText(context, "Just Clicked Add Buddy!", Toast.LENGTH_SHORT).show()
            }
        }

        private fun View.configureVerticalBuddyLayout(position: Int, diver: Diver) {
            diver_card_vertical_clear.setOnClickListener { dismissBuddy(position) }
            diver_card_vertical_avatar.setImageResource(R.drawable.ic_menu_avatar)
            diver_card_vertical_background.setImageResource(R.color.teel_200)
            diver_card_vertical_name.text = diver.firstName
            diver_card_vertical_level.text = diver.certifications?.first()?.certificationName
            diver_card_vertical_add_buddy.setOnClickListener {
                Toast.makeText(context, "Just Clicked Add Buddy!", Toast.LENGTH_SHORT).show()
            }
        }

        private fun View.configureBuddyCountText(diver: Diver) {
            diver_card_horizontal_buddy_count.text =
                if (diver.buddies?.size != SINGULAR_BUDDY_COUNT) {
                    diver.buddies?.size.toString().plus(" Buddies")
                } else {
                    diver.buddies.size.toString().plus(" Buddy")
                }
        }

        private fun dismissBuddy(position: Int) {
            divers.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, divers.size)
        }

        private fun navigateToProfile(view: View, diver: Diver) {
            val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.firstName!!)
            view.findNavController().navigate(directions)
        }
    }

    companion object {
        private const val BUDDY_VERTICAL_LIMIT = 4
        private const val SINGULAR_BUDDY_COUNT = 1
    }
}
