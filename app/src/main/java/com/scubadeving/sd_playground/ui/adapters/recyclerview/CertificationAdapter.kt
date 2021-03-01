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
import com.scubadeving.sd_playground.data.model.certification.CatalogCertification
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertificationAdapter.CertificationViewHolder
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_certification_card_profile.view.profile_cert_card_text
import kotlinx.android.synthetic.main.item_certification_level.view.cert_level_name
import kotlinx.android.synthetic.main.item_certification_level.view.cert_level_specialty_rv

class CertificationAdapter(
    private val catalogCertifications: List<CatalogCertification>,
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

    override fun getItemCount(): Int = catalogCertifications.size

    override fun onBindViewHolder(holder: CertificationViewHolder, position: Int) {
        val certification = catalogCertifications[position]
        holder.bind(certification)
    }

    inner class CertificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(catalogCertification: CatalogCertification) {
            itemView.apply {
                if (orientation) {
                    configureCertificationCatalogLayout(catalogCertification)
                } else {
                    configureCertificationProfileLayout(catalogCertification)
                }
                setOnClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(itemView.context, "Just Clicked Cert Path Item!", Toast.LENGTH_SHORT)
                        .show()
                    navigateToCertificationDetail(it, catalogCertification)
                }
            }
        }

        private fun View.configureCertificationCatalogLayout(catalogCertification: CatalogCertification) {
            cert_level_name.text = catalogCertification.name
            val specialtyLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            specialtyLayoutManager.initialPrefetchItemCount = CERT_LEVEL_PREFETCH_COUNT
            cert_level_specialty_rv.apply {
                layoutManager = specialtyLayoutManager
                adapter = catalogCertification.specialties?.let { SpecialtyAdapter(it) }
                setRecycledViewPool(viewPool)
                val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
                addItemDecoration(dividerItemDecoration)
                onFlingListener = null
                val snapHelper: SnapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(this)
            }
        }

        private fun View.configureCertificationProfileLayout(catalogCertification: CatalogCertification) {
            profile_cert_card_text.text = catalogCertification.name
        }

        private fun navigateToCertificationDetail(view: View, catalogCertification: CatalogCertification) {
            val directions = MainNavigationDirections.actionGlobalCertDetailFragment(catalogCertification.name!!)
            view.findNavController().navigate(directions)
        }
    }

    companion object {
        private const val CERT_LEVEL_PREFETCH_COUNT = 4
    }
}
