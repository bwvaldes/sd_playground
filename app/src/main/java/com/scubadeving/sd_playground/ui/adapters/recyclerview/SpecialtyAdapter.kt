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
import com.scubadeving.sd_playground.data.model.certification.Specialty
import com.scubadeving.sd_playground.databinding.ItemCertificationCardBinding

class SpecialtyAdapter : ListAdapter<Specialty, RecyclerView.ViewHolder>(SpecialtyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyViewHolder {
        return SpecialtyViewHolder(
            ItemCertificationCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val specialty = getItem(position)
        (holder as SpecialtyViewHolder).bind(specialty)
    }

    inner class SpecialtyViewHolder(private val binding: ItemCertificationCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(specialty: Specialty) {
            binding.apply {
                certCardText.text = specialty.name
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Cert Path Specialty Item!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToSpecialtyDetail(it, specialty)
                }
            }
        }

        private fun navigateToSpecialtyDetail(view: View, specialty: Specialty) {
            val directions = MainNavigationDirections.actionGlobalCertDetailFragment(specialty.name!!)
            view.findNavController().navigate(directions)
        }
    }

    private class SpecialtyDiffCallback : DiffUtil.ItemCallback<Specialty>() {

        override fun areItemsTheSame(oldItem: Specialty, newItem: Specialty): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Specialty, newItem: Specialty): Boolean {
            return oldItem == newItem
        }
    }
}
