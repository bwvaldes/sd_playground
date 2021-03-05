package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.adapters.ViewBindingAdapter.setClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.data.model.DiveCenter
import com.scubadeving.sd_playground.databinding.ItemDiveCenterCardBinding
import com.scubadeving.sd_playground.utils.inflate

class DiveCenterAdapter :
    ListAdapter<DiveCenter, RecyclerView.ViewHolder>(DiveCenterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiveCenterViewHolder {
        return DiveCenterViewHolder(
            ItemDiveCenterCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val diveCenter = getItem(position)
        (holder as DiveCenterViewHolder).bind(diveCenter)
    }

    inner class DiveCenterViewHolder(private val binding: ItemDiveCenterCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(diveCenter: DiveCenter) {
            binding.apply {
                diveCenterName.text = diveCenter.name
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Dive Center Item!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    navigateToDiveCenterDetail(it)
                }
            }
        }

        private fun navigateToDiveCenterDetail(view: View) {
            val gmmIntentUri =
                Uri.parse("geo:33.7715323,-118.3633929?q=" + Uri.encode("Dive Center"))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            view.context.startActivity(mapIntent)
        }
    }

    private class DiveCenterDiffCallback : DiffUtil.ItemCallback<DiveCenter>() {

        override fun areItemsTheSame(oldItem: DiveCenter, newItem: DiveCenter): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: DiveCenter, newItem: DiveCenter): Boolean {
            return oldItem == newItem
        }
    }
}
