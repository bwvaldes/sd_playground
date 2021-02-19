package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Specialty
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_cert_card.view.cert_card_text

class SpecialtyAdapter(private val specialties: List<Specialty>) :
    RecyclerView.Adapter<SpecialtyAdapter.SpecialtyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyViewHolder {
        val inflatedView = parent.inflate(R.layout.item_cert_card, false)
        return SpecialtyViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = specialties.size

    override fun onBindViewHolder(holder: SpecialtyViewHolder, position: Int) {
        val specialtyCards = specialties[position]
        holder.bind(specialtyCards, position)
    }

    inner class SpecialtyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                Log.d("RecyclerView", "CLICK!")
                Toast.makeText(
                    itemView.context,
                    "Just Clicked Cert Path Specialty Item!",
                    Toast.LENGTH_SHORT
                ).show()
                navigateToSpecialtyDetail(it)
            }
        }

        fun bind(specialty: Specialty, position: Int) {
            itemView.apply {
                cert_card_text.text = specialty.name
            }
        }

        private fun navigateToSpecialtyDetail(it: View) {
            it.findNavController().navigate(R.id.certDetailFragment)
        }
    }
}