package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.databinding.ItemDetailCardBinding

class ItemDetailAdapter : ListAdapter<String, RecyclerView.ViewHolder>(ItemDetailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDetailViewHolder {
        return ItemDetailViewHolder(
            ItemDetailCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemDetail = getItem(position)
        (holder as ItemDetailViewHolder).bind(itemDetail)
    }

    inner class ItemDetailViewHolder(private val binding: ItemDetailCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(detail: String) {
            binding.apply {
                itemDetailText.text = detail
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Item Detail Card!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private class ItemDetailDiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
