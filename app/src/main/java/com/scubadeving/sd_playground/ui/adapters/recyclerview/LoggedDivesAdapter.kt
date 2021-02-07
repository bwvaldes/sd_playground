package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_logged_dive_card_horizontal.view.*
import kotlinx.android.synthetic.main.item_logged_dive_card_vertical.view.*

class LoggedDivesAdapter(private val loggedDives: List<String>, val orientation: Boolean) :
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

        fun bind(loggedDive: String, position: Int) {
            val logId = (position + 1).toString().plus(". ")
            itemView.apply {
                if (orientation) {
                    logged_dive_site_image.setBackgroundResource(R.drawable.ic_next_steps)
                    logged_dive_rating.text = "3.75 (14)"
                    logged_dive_details.text = logId.plus(loggedDive)
                    logged_dive_date.text = "January 23rd, 2020"
                    logged_dive_depth.text = "44ft"
                    logged_dive_bottom_time.text = "38min"
                } else {
                    logged_dive_map_site_image.setBackgroundResource(R.drawable.ic_next_steps)
                    logged_dive_map_rating.text = "3.75 (14)"
                    logged_dive_map_details.text = logId.plus(loggedDive)
                    logged_dive_map_date.text = "January 23rd, 2020"
                    logged_dive_map_depth.text = "44ft"
                    logged_dive_map_bottom_time.text = "38min"
                }
            }
        }

    }
}