package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.divelog.DiveLog
import com.scubadeving.sd_playground.utils.inflate
import kotlin.time.seconds
import kotlinx.android.synthetic.main.item_divelog_card_horizontal.view.logged_dive_map_bottom_time
import kotlinx.android.synthetic.main.item_divelog_card_horizontal.view.logged_dive_map_date
import kotlinx.android.synthetic.main.item_divelog_card_horizontal.view.logged_dive_map_depth
import kotlinx.android.synthetic.main.item_divelog_card_horizontal.view.logged_dive_map_details
import kotlinx.android.synthetic.main.item_divelog_card_horizontal.view.logged_dive_map_rating
import kotlinx.android.synthetic.main.item_divelog_card_horizontal.view.logged_dive_map_site_image
import kotlinx.android.synthetic.main.item_divelog_card_vertical.view.logged_dive_bottom_time
import kotlinx.android.synthetic.main.item_divelog_card_vertical.view.logged_dive_date
import kotlinx.android.synthetic.main.item_divelog_card_vertical.view.logged_dive_depth
import kotlinx.android.synthetic.main.item_divelog_card_vertical.view.logged_dive_details
import kotlinx.android.synthetic.main.item_divelog_card_vertical.view.logged_dive_rating
import kotlinx.android.synthetic.main.item_divelog_card_vertical.view.logged_dive_site_image

class DiveLogAdapter(private val diveLogs: ArrayList<DiveLog>, val orientation: Boolean) :
    RecyclerView.Adapter<DiveLogAdapter.DiveLogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiveLogViewHolder {
        val inflatedView = if (orientation) {
            parent.inflate(R.layout.item_divelog_card_vertical, false)
        } else {
            parent.inflate(R.layout.item_divelog_card_horizontal, false)
        }
        return DiveLogViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = diveLogs.size

    override fun onBindViewHolder(holderLog: DiveLogViewHolder, position: Int) {
        val loggedDive = diveLogs[position]
        holderLog.bind(loggedDive)
    }

    inner class DiveLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(diveLog: DiveLog) {
            itemView.apply {
                if (orientation) {
                    configureLogbookListLayout(diveLog)
                } else {
                    configureLogbookMapLayout(diveLog)
                }
                setOnClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(itemView.context, "Just Clicked Logged Dive Item!", Toast.LENGTH_SHORT)
                        .show()
                    navigateToDiveLogDetail(it, diveLog)
                }
            }
        }

        private fun View.configureLogbookListLayout(loggedDive: DiveLog) {
            logged_dive_site_image.setBackgroundResource(R.drawable.ic_next_steps)
//            logged_dive_rating.text = loggedDive.diveSite?.rating.toString()
            logged_dive_details.text =
                context.getString(R.string.logbook_details, loggedDive.id, "loggedDive.diveSite?.name")
            logged_dive_date.text = loggedDive.date
            logged_dive_depth.text = loggedDive.depth.toString()
            logged_dive_bottom_time.text = loggedDive.bottomTime.toString()
        }

        private fun View.configureLogbookMapLayout(loggedDive: DiveLog) {
            logged_dive_map_site_image.setBackgroundResource(R.drawable.ic_next_steps)
//            logged_dive_map_rating.text = loggedDive.diveSite?.rating.toString()
            logged_dive_map_details.text =
                context.getString(R.string.logbook_details, loggedDive.id, "loggedDive.diveSite?.name")
            logged_dive_map_date.text = loggedDive.date
            logged_dive_map_depth.text = loggedDive.depth.toString()
            logged_dive_map_bottom_time.text = loggedDive.bottomTime.toString()
        }

        private fun navigateToDiveLogDetail(view: View, diveLog: DiveLog) {
            val directions = MainNavigationDirections.actionGlobalLogbookEntryFragment("diveLog.diveSite?.name!!")
            view.findNavController().navigate(directions)
        }
    }
}
