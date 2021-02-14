package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.SavedList
import com.scubadeving.sd_playground.data.Specialty
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_cert_card.view.cert_card_text
import kotlinx.android.synthetic.main.item_saved_card.view.*

class SavedListAdapter(private val savedLists: List<SavedList>) :
    RecyclerView.Adapter<SavedListAdapter.SavedListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedListViewHolder {
        val inflatedView = parent.inflate(R.layout.item_saved_card, false)
        return SavedListViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = savedLists.size

    override fun onBindViewHolder(holder: SavedListViewHolder, position: Int) {
        val savedList = savedLists[position]
        holder.bind(savedList, position)
    }

    inner class SavedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(
                itemView.context,
                "Just Clicked Saved List Item!",
                Toast.LENGTH_SHORT
            ).show()
//            view.findNavController().navigate(R.id.savedListFragment)
        }

        fun bind(savedList: SavedList, position: Int) {
            itemView.apply {
                saved_list_text.text = savedList.name
            }
        }
    }
}