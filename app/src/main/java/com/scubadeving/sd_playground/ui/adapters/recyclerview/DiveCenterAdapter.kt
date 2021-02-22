package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveCenter
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_dive_center_card.view.*

class DiveCenterAdapter(private val diveCenters: List<DiveCenter>) :
    RecyclerView.Adapter<DiveCenterAdapter.DiveCenterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiveCenterViewHolder {
        val inflatedView = parent.inflate(R.layout.item_dive_center_card, false)
        return DiveCenterViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = diveCenters.size

    override fun onBindViewHolder(holder: DiveCenterViewHolder, position: Int) {
        val diveCenter = diveCenters[position]
        holder.bind(diveCenter, position)
    }

    inner class DiveCenterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(diveCenter: DiveCenter, position: Int) {
            itemView.apply {
                dive_center_name.text = diveCenter.name
                setOnClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(itemView.context, "Just Clicked Dive Center Item!", Toast.LENGTH_SHORT)
                        .show()
                    navigateToDiveCenterDetail(it)
                }
            }
        }

        private fun navigateToDiveCenterDetail(it: View) {
            val gmmIntentUri =
                Uri.parse("geo:33.7715323,-118.3633929?q=" + Uri.encode("Dive Center"))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            it.context.startActivity(mapIntent)
        }
    }
}