package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveSite
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_dive_site_card.view.*

class DiveSiteAdapter(private val diveSites: List<DiveSite>) :
    RecyclerView.Adapter<DiveSiteAdapter.DiveSiteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiveSiteViewHolder {
        val inflatedView = parent.inflate(R.layout.item_dive_site_card, false)
        return DiveSiteViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = diveSites.size

    override fun onBindViewHolder(holder: DiveSiteViewHolder, position: Int) {
        val sites = diveSites[position]
        holder.bind(sites, position)
    }

    inner class DiveSiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(itemView.context, "Just Clicked Dive Site Item!", Toast.LENGTH_SHORT)
                .show()
            view.findNavController().navigate(R.id.diveSiteDetailFragment)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(diveSite: DiveSite, position: Int) {
            itemView.apply {
                dive_site_card_favorite.apply {
                    setOnCheckedChangeListener { _, isFavorite ->
                        if (isFavorite) {
                            Toast.makeText(context, "Favorited!", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Unfavorited!", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                when (position) {
                    1 -> backgroundTintList = ColorStateList.valueOf(Color.RED)
                    2 -> backgroundTintList = ColorStateList.valueOf(Color.MAGENTA)
                    3 -> {
                        backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                        dive_site_card_popularity.visibility = View.VISIBLE
                    }
                }
                dive_site_card_rating.text = "${diveSite.rating} (${diveSite.reviews})"
                dive_site_card_name.text = diveSite.name
                dive_site_card_location.text = diveSite.location
            }
        }
    }
}