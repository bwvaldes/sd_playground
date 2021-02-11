package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.ExploreFilter
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_explore_filter_card.view.*

class ExploreFilterAdapter(private val filters: List<ExploreFilter>) :
    RecyclerView.Adapter<ExploreFilterAdapter.ExploreFilterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreFilterViewHolder {
        val inflatedView = parent.inflate(R.layout.item_explore_filter_card, false)
        return ExploreFilterViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = filters.size

    override fun onBindViewHolder(holder: ExploreFilterViewHolder, position: Int) {
        val filter = filters[position]
        holder.bind(filter, position)
    }

    inner class ExploreFilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(
                itemView.context,
                "Just Clicked Explore Filter Card!",
                Toast.LENGTH_SHORT
            ).show()
            view.findNavController().navigate(R.id.exploreDetailsFilteredFragment)
        }

        fun bind(filter: ExploreFilter, position: Int) {
            itemView.apply {
                explore_filter_card_text.text = filter.name
            }
        }
    }
}