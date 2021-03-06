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
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.divelog.DiveLog
import com.scubadeving.sd_playground.data.model.divelog.DiveLog.Companion.VIEW_TYPE_LOG
import com.scubadeving.sd_playground.data.model.divelog.DiveLog.Companion.VIEW_TYPE_MAP
import com.scubadeving.sd_playground.databinding.ItemDivelogCardHorizontalBinding
import com.scubadeving.sd_playground.databinding.ItemDivelogCardVerticalBinding

class DiveLogAdapter : ListAdapter<DiveLog, RecyclerView.ViewHolder>(DiveLogDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            VIEW_TYPE_LOG -> DiveLogVerticalViewHolder(
                ItemDivelogCardVerticalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_MAP -> DiveLogHorizontalViewHolder(
                ItemDivelogCardHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).viewType

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val diveLog = getItem(position)
        when (holder) {
            is DiveLogVerticalViewHolder -> holder.bind(diveLog)
            is DiveLogHorizontalViewHolder -> holder.bind(diveLog)
            else -> throw IllegalAccessException()
        }
    }

    inner class DiveLogVerticalViewHolder(private val binding: ItemDivelogCardVerticalBinding) :
        BaseViewHolder<DiveLog>(binding.root) {

        override fun bind(diveLog: DiveLog) {
            binding.apply {
                loggedDiveSiteImage.setBackgroundResource(R.drawable.ic_next_steps)
                loggedDiveRating.text = "diveLog.diveSite?.rating.toString()"
                loggedDiveDetails.text =
                    binding.root.context.getString(
                        R.string.logbook_details,
                        diveLog.id,
                        "loggedDive.diveSite?.name"
                    )
                loggedDiveDate.text = diveLog.date
                loggedDiveDepth.text = diveLog.depth.toString()
                loggedDiveBottomTime.text = diveLog.bottomTime.toString()
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Logged Dive Item!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    navigateToDiveLogDetail(it, diveLog)
                }
            }
        }
    }

    inner class DiveLogHorizontalViewHolder(private val binding: ItemDivelogCardHorizontalBinding) :
        BaseViewHolder<DiveLog>(binding.root) {

        override fun bind(diveLog: DiveLog) {
            binding.apply {
                loggedDiveMapSiteImage.setBackgroundResource(R.drawable.ic_next_steps)
                loggedDiveMapRating.text = "diveLog.diveSite?.rating.toString()"
                loggedDiveMapDetails.text =
                    binding.root.context.getString(
                        R.string.logbook_details,
                        diveLog.id,
                        "loggedDive.diveSite?.name"
                    )
                loggedDiveMapDate.text = diveLog.date
                loggedDiveMapDepth.text = diveLog.depth.toString()
                loggedDiveMapBottomTime.text = diveLog.bottomTime.toString()
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Logged Dive Item!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    navigateToDiveLogDetail(it, diveLog)
                }
            }
        }
    }

    private fun navigateToDiveLogDetail(view: View, diveLog: DiveLog) {
        val directions =
            MainNavigationDirections.actionGlobalLogbookEntryFragment("diveLog.diveSite?.name!!")
        view.findNavController().navigate(directions)
    }

    private class DiveLogDiffCallback : DiffUtil.ItemCallback<DiveLog>() {

        override fun areItemsTheSame(oldItem: DiveLog, newItem: DiveLog): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DiveLog, newItem: DiveLog): Boolean {
            return oldItem == newItem
        }
    }
}
