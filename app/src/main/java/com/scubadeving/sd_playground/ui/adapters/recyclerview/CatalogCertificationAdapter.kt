package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.data.model.certification.CatalogCertification
import com.scubadeving.sd_playground.databinding.ItemCertificationLevelBinding

class CatalogCertificationAdapter :
    ListAdapter<CatalogCertification, RecyclerView.ViewHolder>(CatalogCertificationDiffCallback()) {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatalogCertificationViewHolder {
        return CatalogCertificationViewHolder(
            ItemCertificationLevelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val catalogCertificationLevel = getItem(position)
        (holder as CatalogCertificationViewHolder).bindCatalog(catalogCertificationLevel)
    }

    inner class CatalogCertificationViewHolder(private val binding: ItemCertificationLevelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindCatalog(catalogCertification: CatalogCertification) {
            binding.apply {
                configureCertificationCatalogLayout(catalogCertification)
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Cert Path Item!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    navigateToCertificationDetail(it, "catalogCertification")
                }
            }
        }

        private fun ItemCertificationLevelBinding.configureCertificationCatalogLayout(
            catalogCertification: CatalogCertification
        ) {
            certLevelName.text = catalogCertification.name
            val specialtyLayoutManager = LinearLayoutManager(binding.root.context, HORIZONTAL, false)
            specialtyLayoutManager.initialPrefetchItemCount = CERT_LEVEL_PREFETCH_COUNT
            certLevelSpecialtyRv.apply {
                layoutManager = specialtyLayoutManager
                adapter = catalogCertification.specialties?.let {
                    val adapter = SpecialtyAdapter()
                    adapter.submitList(it)
                    adapter
                }
                setRecycledViewPool(viewPool)
                val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
                addItemDecoration(dividerItemDecoration)
                onFlingListener = null
                val snapHelper: SnapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(this)
            }
        }
    }

    private fun navigateToCertificationDetail(view: View, certificationId: String) {
        val directions =
            MainNavigationDirections.actionGlobalCertDetailFragment(certificationId)
        view.findNavController().navigate(directions)
    }

    private class CatalogCertificationDiffCallback : DiffUtil.ItemCallback<CatalogCertification>() {

        override fun areItemsTheSame(
            oldItem: CatalogCertification,
            newItem: CatalogCertification
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CatalogCertification,
            newItem: CatalogCertification
        ): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private const val CERT_LEVEL_PREFETCH_COUNT = 4
    }
}
