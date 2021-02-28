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
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.sites.DiveSite
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_dive_site_card_large.view.dive_site_card_large_favorite
import kotlinx.android.synthetic.main.item_dive_site_card_large.view.dive_site_card_large_location
import kotlinx.android.synthetic.main.item_dive_site_card_large.view.dive_site_card_large_name
import kotlinx.android.synthetic.main.item_dive_site_card_large.view.dive_site_card_large_rating
import kotlinx.android.synthetic.main.item_dive_site_card_small.view.dive_site_card_small_favorite
import kotlinx.android.synthetic.main.item_dive_site_card_small.view.dive_site_card_small_location
import kotlinx.android.synthetic.main.item_dive_site_card_small.view.dive_site_card_small_name
import kotlinx.android.synthetic.main.item_dive_site_card_small.view.dive_site_card_small_popularity
import kotlinx.android.synthetic.main.item_dive_site_card_small.view.dive_site_card_small_rating

class DiveSiteAdapter(private val diveSites: List<DiveSite>, val orientation: Boolean = true) :
    RecyclerView.Adapter<DiveSiteAdapter.DiveSiteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiveSiteViewHolder {
        val layout = if (orientation) R.layout.item_dive_site_card_small else R.layout.item_dive_site_card_large
        val inflatedView = parent.inflate(layout, false)
        return DiveSiteViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = diveSites.size

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: DiveSiteViewHolder, position: Int) {
        val sites = diveSites[position]
        holder.bind(sites, position)
    }

    inner class DiveSiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(diveSite: DiveSite, position: Int) {
            itemView.apply {
                if (orientation) {
                    configureDiveSitesSmallLayout(position, diveSite)
                } else {
                    configureDiveSitesLargeLayout(diveSite)
                }
                setOnClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(itemView.context, "Just Clicked Dive Site Item!", Toast.LENGTH_SHORT)
                        .show()
                    navigateToDiveSiteDetail(it, diveSite)
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        private fun View.configureDiveSitesSmallLayout(position: Int, diveSite: DiveSite) {
            dive_site_card_small_favorite.apply {
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
                    dive_site_card_small_popularity.visibility = View.VISIBLE
                }
            }
            dive_site_card_small_rating.text = context.getString(
                R.string.dive_site_rating,
                diveSite.rating,
                diveSite.reviews
            )
            dive_site_card_small_name.text = diveSite.name
//            dive_site_card_small_location.text = diveSite.location
        }

        private fun View.configureDiveSitesLargeLayout(diveSite: DiveSite) {
            dive_site_card_large_favorite.apply {
                setOnCheckedChangeListener { _, isFavorite ->
                    if (isFavorite) {
                        Toast.makeText(context, "Favorited!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Unfavorited!", Toast.LENGTH_LONG).show()
                    }
                }
            }
            dive_site_card_large_rating.text = context.getString(
                R.string.dive_site_rating,
                diveSite.rating,
                diveSite.reviews
            )
            dive_site_card_large_name.text = diveSite.name
//            dive_site_card_large_location.text = diveSite.location
        }

        private fun navigateToDiveSiteDetail(view: View, diveSite: DiveSite) {
            val directions = MainNavigationDirections.actionGlobalDiveSiteDetailFragment(diveSite.name!!)
            view.findNavController().navigate(directions)
        }
    }
}
