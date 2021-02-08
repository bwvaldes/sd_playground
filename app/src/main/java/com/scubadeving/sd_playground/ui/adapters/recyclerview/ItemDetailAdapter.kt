package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_detail_card.view.*

class ItemDetailAdapter(private val details: List<String>) :
    RecyclerView.Adapter<ItemDetailAdapter.ItemDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDetailViewHolder {
        val inflatedView = parent.inflate(R.layout.item_detail_card, false)
        return ItemDetailViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = details.size

    override fun onBindViewHolder(holder: ItemDetailViewHolder, position: Int) {
        val details = details[position]
        holder.bind(details, position)
    }

    inner class ItemDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(
                itemView.context,
                "Just Clicked Item Detail Card!",
                Toast.LENGTH_SHORT
            ).show()
        }

        fun bind(detail: String, position: Int) {
            itemView.apply {
                item_detail_text.text = detail
            }
        }
    }
}