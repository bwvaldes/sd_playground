package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_cert_card.view.cert_card_text

class DiveSiteAdapter(private val diveSites: List<String>) :
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

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(itemView.context, "Just Clicked Dive Site Item!", Toast.LENGTH_SHORT)
                .show()
            v.findNavController().navigate(R.id.diveSiteDetailFragment)
        }

        fun bind(diveSite: String, position: Int) {
            itemView.apply {
                cert_card_text.text = diveSite
            }
        }

    }
}