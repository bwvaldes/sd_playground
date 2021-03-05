package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.data.model.SavedList
import com.scubadeving.sd_playground.databinding.ItemSavedListCardBinding
import com.scubadeving.sd_playground.ui.main.saved.SavedFragmentDirections

class SavedListAdapter : ListAdapter<SavedList, RecyclerView.ViewHolder>(SavedListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedListViewHolder {
        return SavedListViewHolder(
            ItemSavedListCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val savedList = getItem(position)
        (holder as SavedListViewHolder).bind(savedList)
    }

    inner class SavedListViewHolder(private val binding: ItemSavedListCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(savedList: SavedList) {
            binding.apply {
                savedListText.text = savedList.name
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Saved List Item!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToSavedList(it, savedList)
                }
            }
        }

        private fun navigateToSavedList(view: View, savedList: SavedList) {
            val directions = SavedFragmentDirections.actionSavedFragmentToSavedDetailFragment(savedList.name)
            view.findNavController().navigate(directions)
        }
    }

    private class SavedListDiffCallback : DiffUtil.ItemCallback<SavedList>() {

        override fun areItemsTheSame(oldItem: SavedList, newItem: SavedList): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SavedList, newItem: SavedList): Boolean {
            return oldItem == newItem
        }
    }
}
