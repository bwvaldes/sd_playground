package com.scubadeving.sd_playground.ui.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.CertLevelAdapter.CertPathViewHolder
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_cert_level.view.*


class CertLevelAdapter(private val certLevels: List<String>) :
    RecyclerView.Adapter<CertPathViewHolder>() {

    val certCards: List<String> = listOf("Wreck", "Ice", "Cave", "Night")
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: SpecialtyAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertPathViewHolder {
        val inflatedView = parent.inflate(R.layout.item_cert_level, false)
        return CertPathViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = certLevels.size

    override fun onBindViewHolder(holder: CertPathViewHolder, position: Int) {
        val levels = certLevels[position]
        holder.bind(levels, position)
    }

    inner class CertPathViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(itemView.context, "Just Clicked Cert Path Item!", Toast.LENGTH_SHORT)
                .show()
        }

        fun bind(certLevel: String, position: Int) {
            itemView.apply {
                cert_level_name.text = certLevel
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                cert_level_specialty_rv.layoutManager = layoutManager
                adapter = SpecialtyAdapter(certCards)
                cert_level_specialty_rv.adapter = adapter
                val dividerItemDecoration = DividerItemDecoration(
                    cert_level_specialty_rv.getContext(),
                    layoutManager.orientation
                )
                cert_level_specialty_rv.addItemDecoration(dividerItemDecoration)
            }
        }

    }

}
