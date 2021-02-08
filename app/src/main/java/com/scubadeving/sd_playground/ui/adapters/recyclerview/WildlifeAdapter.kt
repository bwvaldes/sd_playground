package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Wildlife
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_wildlife_card_small.view.*

class WildlifeAdapter(private val wildlife: List<Wildlife>) :
    RecyclerView.Adapter<WildlifeAdapter.WildlifeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WildlifeViewHolder {
        val inflatedView = parent.inflate(R.layout.item_wildlife_card_small, false)
        return WildlifeViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = wildlife.size

    override fun onBindViewHolder(holder: WildlifeViewHolder, position: Int) {
        val wildlife = wildlife[position]
        holder.bind(wildlife, position)
    }

    inner class WildlifeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(itemView.context, "Just Clicked Wildlife Item!", Toast.LENGTH_SHORT)
                .show()
            view.findNavController().navigate(R.id.wildlifeFragment)
        }

        fun bind(wildlife: Wildlife, position: Int) {
            itemView.apply {
                wildlife_card_name.text = wildlife.commonName
            }
        }

    }
}