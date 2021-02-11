package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.LinearLayoutManager.*
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Certification
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertificationAdapter.CertificationViewHolder
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_cert_card_profile.view.*
import kotlinx.android.synthetic.main.item_cert_level.view.*

class CertificationAdapter(
    private val certifications: List<Certification>,
    private val orientation: Boolean = true
) :
    RecyclerView.Adapter<CertificationViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertificationViewHolder {
        val inflatedView = if (orientation) {
            parent.inflate(R.layout.item_cert_level, false)
        } else {
            parent.inflate(R.layout.item_cert_card_profile, false)
        }
        return CertificationViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = certifications.size

    override fun onBindViewHolder(holder: CertificationViewHolder, position: Int) {
        val certification = certifications[position]
        holder.bind(certification, position)
    }

    inner class CertificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
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

        fun bind(certification: Certification, position: Int) {
            itemView.apply {
                if (orientation) {
                    configureCatalogCertificationsLayout(certification)
                } else {
                    configureProfileCertificationsLayout(certification)
                }
            }
        }

        private fun View.configureCatalogCertificationsLayout(certifications: Certification) {
            cert_level_name.text = certifications.name
            val specialtyLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            specialtyLayoutManager.initialPrefetchItemCount = CERT_LEVEL_PREFETCH_COUNT
            cert_level_specialty_rv.apply {
                layoutManager = specialtyLayoutManager
                adapter = certifications.specialties?.let { SpecialtyAdapter(it) }
                setRecycledViewPool(viewPool)
                val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
                addItemDecoration(dividerItemDecoration)
                onFlingListener = null
                val snapHelper: SnapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(this)
            }
        }

        private fun View.configureProfileCertificationsLayout(certifications: Certification) {
            profile_cert_card_text.text = certifications.name
        }
    }

    companion object {
        private const val CERT_LEVEL_PREFETCH_COUNT = 4
    }
}