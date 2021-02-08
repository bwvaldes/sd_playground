package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Certification
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertificationLevelAdapter.CertPathViewHolder
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_cert_level.view.*


class CertificationLevelAdapter(private val certifications: List<Certification>) :
    RecyclerView.Adapter<CertPathViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertPathViewHolder {
        val inflatedView = parent.inflate(R.layout.item_cert_level, false)
        return CertPathViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = certifications.size

    override fun onBindViewHolder(holder: CertPathViewHolder, position: Int) {
        val certifications = certifications[position]
        holder.itemView.apply {
            cert_level_name.text = certifications.name
            val specialtyLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            specialtyLayoutManager.initialPrefetchItemCount = 4
            cert_level_specialty_rv.apply {
                layoutManager = specialtyLayoutManager
                adapter = SpecialtyAdapter(certifications.specialties)
                setRecycledViewPool(viewPool)
                val dividerItemDecoration =
                    DividerItemDecoration(context, specialtyLayoutManager.orientation)
                addItemDecoration(dividerItemDecoration)
                onFlingListener = null
                val snapHelper: SnapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(this)
            }
        }
    }

    inner class CertPathViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(itemView.context, "Just Clicked Cert Path Item!", Toast.LENGTH_SHORT)
                .show()
            view.findNavController().navigate(R.id.certDetailFragment)
        }
    }
}