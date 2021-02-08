package com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Certification
import com.scubadeving.sd_playground.ui.adapters.recyclerview.SpecialtyAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.CertLevelAdapter.CertPathViewHolder
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.fragment_catalog.*
import kotlinx.android.synthetic.main.item_cert_level.view.*


class CertLevelAdapter(private val certLevels: List<String>) :
    RecyclerView.Adapter<CertPathViewHolder>() {

    val certCards: List<Certification> = listOf(
        Certification("Wreck"),
        Certification("Ice"),
        Certification("Cave"),
        Certification("Night")
    )
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
            v.findNavController().navigate(R.id.certDetailFragment)
        }

        fun bind(certLevel: String, position: Int) {
            itemView.apply {
                cert_level_name.text = certLevel
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                cert_level_specialty_rv.layoutManager = layoutManager
                adapter =
                    SpecialtyAdapter(
                        certCards
                    )
                cert_level_specialty_rv.adapter = adapter
                val dividerItemDecoration = DividerItemDecoration(
                    cert_level_specialty_rv.context,
                    layoutManager.orientation
                )
                cert_level_specialty_rv.addItemDecoration(dividerItemDecoration)
                cert_level_specialty_rv.onFlingListener = null
                val snapHelper: SnapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(cert_level_specialty_rv)
            }
        }

    }

}
