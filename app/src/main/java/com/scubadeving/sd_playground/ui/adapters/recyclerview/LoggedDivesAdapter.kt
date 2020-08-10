package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_dive_center_card.view.*
import kotlinx.android.synthetic.main.item_logged_dive_card.view.*

class LoggedDivesAdapter(private val loggedDives: List<String>) :
    RecyclerView.Adapter<LoggedDivesAdapter.LoggedDiveViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoggedDiveViewHolder {
        val inflatedView = parent.inflate(R.layout.item_logged_dive_card, false)
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
                .show()   }

        fun bind(loggedDive: String, position: Int) {
            itemView.apply {
                logged_dive_text.text = loggedDive
            }
        }

    }
}