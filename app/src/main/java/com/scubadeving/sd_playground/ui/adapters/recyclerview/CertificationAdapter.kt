package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Certification
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertificationAdapter.CertificationViewHolder
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_certification_card_profile.view.profile_cert_card_text
import kotlinx.android.synthetic.main.item_certification_level.view.cert_level_name
import kotlinx.android.synthetic.main.item_certification_level.view.cert_level_specialty_rv

class CertificationAdapter(
    private val certifications: List<Certification>,
    private val orientation: Boolean = true
) : RecyclerView.Adapter<CertificationViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertificationViewHolder {
        val inflatedView = if (orientation) {
            parent.inflate(R.layout.item_certification_level, false)
        } else {
            parent.inflate(R.layout.item_certification_card_profile, false)
        }
        return CertificationViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = certifications.size

    override fun onBindViewHolder(holder: CertificationViewHolder, position: Int) {
        val certification = certifications[position]
        holder.bind(certification)
    }

    inner class CertificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(certification: Certification) {
            itemView.apply {
                if (orientation) {
                    configureCertificationCatalogLayout(certification)
                } else {
                    configureCertificationProfileLayout(certification)
                }
                setOnClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(itemView.context, "Just Clicked Cert Path Item!", Toast.LENGTH_SHORT)
                        .show()
                    navigateToCertificationDetail(it, certification)
                }
            }
        }

        private fun View.configureCertificationCatalogLayout(certification: Certification) {
            cert_level_name.text = certification.name
            val specialtyLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            specialtyLayoutManager.initialPrefetchItemCount = CERT_LEVEL_PREFETCH_COUNT
            cert_level_specialty_rv.apply {
                layoutManager = specialtyLayoutManager
                adapter = certification.specialties?.let { SpecialtyAdapter(it) }
                setRecycledViewPool(viewPool)
                val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
                addItemDecoration(dividerItemDecoration)
                onFlingListener = null
                val snapHelper: SnapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(this)
            }
        }

        private fun View.configureCertificationProfileLayout(certification: Certification) {
            profile_cert_card_text.text = certification.name
        }

        private fun navigateToCertificationDetail(view: View, certification: Certification) {
            val directions = MainNavigationDirections.actionGlobalCertDetailFragment(certification.name)
            view.findNavController().navigate(directions)
        }
    }

    companion object {
        private const val CERT_LEVEL_PREFETCH_COUNT = 4
    }
}
