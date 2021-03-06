package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.sites.DiveSite
import com.scubadeving.sd_playground.data.model.sites.DiveSite.Companion.VIEW_TYPE_EXPANDED
import com.scubadeving.sd_playground.data.model.sites.DiveSite.Companion.VIEW_TYPE_COMPACT
import com.scubadeving.sd_playground.databinding.ItemDiveSiteCardLargeBinding
import com.scubadeving.sd_playground.databinding.ItemDiveSiteCardSmallBinding

class DiveSiteAdapter : ListAdapter<DiveSite, RecyclerView.ViewHolder>(DiveSiteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            VIEW_TYPE_COMPACT -> {
                DiveSiteCompactViewHolder(
                    ItemDiveSiteCardSmallBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_EXPANDED -> {
                DiveSiteExpandedViewHolder(
                    ItemDiveSiteCardLargeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).viewType

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val diveSite = getItem(position)
        when (holder) {
            is DiveSiteCompactViewHolder -> holder.bind(diveSite)
            is DiveSiteExpandedViewHolder -> holder.bind(diveSite)
            else -> throw IllegalArgumentException()
        }
    }

    inner class DiveSiteCompactViewHolder(private val binding: ItemDiveSiteCardSmallBinding) :
        BaseViewHolder<DiveSite>(binding.root) {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun bind(diveSite: DiveSite) {
            binding.apply {
                configureDiveSitesSmallLayout(position, diveSite)
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Dive Site Item!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToDiveSiteDetail(it, diveSite)
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        private fun ItemDiveSiteCardSmallBinding.configureDiveSitesSmallLayout(
            position: Int,
            diveSite: DiveSite
        ) {
            diveSiteCardSmallFavorite.apply {
                setOnCheckedChangeListener { _, isFavorite ->
                    if (isFavorite) {
                        Toast.makeText(context, "Favorited!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Unfavorited!", Toast.LENGTH_LONG).show()
                    }
                }
            }
            when (position) {
                1 -> binding.root.backgroundTintList = ColorStateList.valueOf(Color.RED)
                2 -> binding.root.backgroundTintList = ColorStateList.valueOf(Color.MAGENTA)
                3 -> {
                    binding.root.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                    diveSiteCardSmallPopularity.visibility = View.VISIBLE
                }
            }
            diveSiteCardSmallRating.text = binding.root.context.getString(
                R.string.dive_site_rating,
                diveSite.rating,
                diveSite.reviews
            )
            diveSiteCardSmallName.text = diveSite.name
            diveSiteCardSmallLocation.text = diveSite.location.toString()
        }
    }

    inner class DiveSiteExpandedViewHolder(private val binding: ItemDiveSiteCardLargeBinding) :
        BaseViewHolder<DiveSite>(binding.root) {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun bind(diveSite: DiveSite) {
            binding.apply {
                configureDiveSitesLargeLayout(diveSite)
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Dive Site Item!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToDiveSiteDetail(it, diveSite)
                }
            }
        }

        private fun ItemDiveSiteCardLargeBinding.configureDiveSitesLargeLayout(diveSite: DiveSite) {
            diveSiteCardLargeFavorite.apply {
                setOnCheckedChangeListener { _, isFavorite ->
                    if (isFavorite) {
                        Toast.makeText(context, "Favorited!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Unfavorited!", Toast.LENGTH_LONG).show()
                    }
                }
            }
            diveSiteCardLargeRating.text = binding.root.context.getString(
                R.string.dive_site_rating,
                diveSite.rating,
                diveSite.reviews
            )
            diveSiteCardLargeName.text = diveSite.name
            diveSiteCardLargeLocation.text = diveSite.location.toString()
        }
    }

    private fun navigateToDiveSiteDetail(view: View, diveSite: DiveSite) {
        val directions =
            MainNavigationDirections.actionGlobalDiveSiteDetailFragment(diveSite.name!!)
        view.findNavController().navigate(directions)
    }

    private class DiveSiteDiffCallback : DiffUtil.ItemCallback<DiveSite>() {

        override fun areItemsTheSame(oldItem: DiveSite, newItem: DiveSite): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: DiveSite, newItem: DiveSite): Boolean {
            return oldItem == newItem
        }
    }
}
