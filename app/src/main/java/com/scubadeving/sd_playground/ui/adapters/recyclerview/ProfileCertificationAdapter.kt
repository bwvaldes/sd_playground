package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.data.model.diver.Certification
import com.scubadeving.sd_playground.databinding.ItemCertificationCardProfileBinding

class ProfileCertificationAdapter :
    ListAdapter<Certification, RecyclerView.ViewHolder>(ProfileCertificationDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileCertificationViewHolder {
        return ProfileCertificationViewHolder(
            ItemCertificationCardProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val profileCertificationLevel = getItem(position)
        (holder as ProfileCertificationViewHolder).bind(profileCertificationLevel)
    }

    inner class ProfileCertificationViewHolder(private val binding: ItemCertificationCardProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(certification: Certification) {
            binding.apply {
                profileCertCardText.text = certification.certificationName
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Cert Path Item!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    navigateToCertificationDetail(it, "profileCertification")
                }
            }
        }
    }

    private fun navigateToCertificationDetail(view: View, certificationId: String) {
        val directions =
            MainNavigationDirections.actionGlobalCertDetailFragment(certificationId)
        view.findNavController().navigate(directions)
    }

    private class ProfileCertificationDiffCallback : DiffUtil.ItemCallback<Certification>() {

        override fun areItemsTheSame(
            oldItem: Certification,
            newItem: Certification
        ): Boolean {
            return oldItem.certificationName == newItem.certificationName
        }

        override fun areContentsTheSame(
            oldItem: Certification,
            newItem: Certification
        ): Boolean {
            return oldItem == newItem
        }
    }
}
