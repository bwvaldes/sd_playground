package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.SavedList
import com.scubadeving.sd_playground.ui.main.saved.SavedFragmentDirections
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_saved_list_card.view.saved_list_text

class SavedListAdapter(private val savedLists: List<SavedList>) :
    RecyclerView.Adapter<SavedListAdapter.SavedListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedListViewHolder {
        val inflatedView = parent.inflate(R.layout.item_saved_list_card, false)
        return SavedListViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = savedLists.size

    override fun onBindViewHolder(holder: SavedListViewHolder, position: Int) {
        val savedList = savedLists[position]
        holder.bind(savedList)
    }

    inner class SavedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(savedList: SavedList) {
            itemView.apply {
                saved_list_text.text = savedList.name
                setOnClickListener {
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
}
