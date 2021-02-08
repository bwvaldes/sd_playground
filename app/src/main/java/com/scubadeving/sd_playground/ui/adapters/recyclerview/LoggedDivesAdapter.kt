package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveLog
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_logged_dive_card_horizontal.view.*
import kotlinx.android.synthetic.main.item_logged_dive_card_vertical.view.*

class LoggedDivesAdapter(private val loggedDives: List<DiveLog>, val orientation: Boolean) :
    RecyclerView.Adapter<LoggedDivesAdapter.LoggedDiveViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoggedDiveViewHolder {
        val inflatedView = if (orientation) {
            parent.inflate(R.layout.item_logged_dive_card_vertical, false)
        } else {
            parent.inflate(R.layout.item_logged_dive_card_horizontal, false)

        }
        return LoggedDiveViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = loggedDives.size

    override fun onBindViewHolder(holder: LoggedDiveViewHolder, position: Int) {
        val loggedDive = loggedDives[position]
        holder.bind(loggedDive, position)
    }

    inner class LoggedDiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(itemView.context, "Just Clicked Logged Dive Item!", Toast.LENGTH_SHORT)
                .show()
            v.findNavController().navigate(R.id.logbookEntryFragment)
        }

        fun bind(loggedDive: DiveLog, position: Int) {
            itemView.apply {
                if (orientation) {
                    logged_dive_site_image.setBackgroundResource(R.drawable.ic_next_steps)
                    logged_dive_rating.text = loggedDive.rating.toString()
                    logged_dive_details.text = "${loggedDive.id}. ${loggedDive.diveSite}"
                    logged_dive_date.text = loggedDive.date
                    logged_dive_depth.text = loggedDive.depth
                    logged_dive_bottom_time.text = loggedDive.bottomTime
                } else {
                    logged_dive_map_site_image.setBackgroundResource(R.drawable.ic_next_steps)
                    logged_dive_map_rating.text = loggedDive.rating.toString()
                    logged_dive_map_details.text = "${loggedDive.id}. ${loggedDive.diveSite}"
                    logged_dive_map_date.text = loggedDive.date
                    logged_dive_map_depth.text = loggedDive.depth
                    logged_dive_map_bottom_time.text = loggedDive.bottomTime
                }
            }
        }

    }
}