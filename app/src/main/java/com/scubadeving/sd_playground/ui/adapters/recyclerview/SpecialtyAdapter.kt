package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_cert_card.view.cert_card_text

class SpecialtyAdapter(private val certCards: List<String>) :
    RecyclerView.Adapter<SpecialtyAdapter.CertSpecialtyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertSpecialtyViewHolder {
        val inflatedView = parent.inflate(R.layout.item_cert_card, false)
        return CertSpecialtyViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = certCards.size

    override fun onBindViewHolder(holder: CertSpecialtyViewHolder, position: Int) {
        val cards = certCards[position]
        holder.bind(cards, position)
    }

    inner class CertSpecialtyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(itemView.context, "Just Clicked Cert Path Specialty Item!", Toast.LENGTH_SHORT)
                .show()
        }

        fun bind(certCard: String, position: Int) {
            itemView.apply {
                cert_card_text.text = certCard
            }
        }

    }
}