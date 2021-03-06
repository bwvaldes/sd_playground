package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.InboxNotification
import com.scubadeving.sd_playground.data.model.InboxNotification.Companion.NOTIFICATION_TYPE_DASHBOARD
import com.scubadeving.sd_playground.data.model.InboxNotification.Companion.NOTIFICATION_TYPE_INBOX
import com.scubadeving.sd_playground.databinding.ItemInboxNotificationCardDashboardBinding
import com.scubadeving.sd_playground.databinding.ItemInboxNotificationCardListBinding

class NotificationAdapter(val notifications: ArrayList<InboxNotification>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            NOTIFICATION_TYPE_DASHBOARD -> {
                NotificationsDashboardViewHolder(
                    ItemInboxNotificationCardDashboardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            NOTIFICATION_TYPE_INBOX -> {
                NotificationsInboxViewHolder(
                    ItemInboxNotificationCardListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = notifications.size

    override fun getItemViewType(position: Int): Int = notifications[position].notificationType

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val notification = notifications[position]
        when (holder) {
            is NotificationsDashboardViewHolder -> holder.bind(notification)
            is NotificationsInboxViewHolder -> holder.bind(notification)
            else -> throw IllegalArgumentException()
        }
    }

    inner class NotificationsDashboardViewHolder(private val binding: ItemInboxNotificationCardDashboardBinding) :
        BaseViewHolder<InboxNotification>(binding.root) {

        override fun bind(inboxNotification: InboxNotification) {
            binding.apply {
                notificationCardDashboardClear.setOnClickListener { dismissNotification(position) }
                notificationCardDashboardDate.text = inboxNotification.date
                notificationCardDashboardData.text = inboxNotification.data
                notificationCardDashboardCount.text = notifications.size.toString()
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        binding.root.context,
                        "Just Clicked Dashboard Notification Card Item!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToNotificationDetail(it)
                }
            }
        }
    }

    inner class NotificationsInboxViewHolder(private val binding: ItemInboxNotificationCardListBinding) :
        BaseViewHolder<InboxNotification>(binding.root) {

        override fun bind(inboxNotification: InboxNotification) {
            binding.apply {
                notificationCardListDate.text = inboxNotification.date
                notificationCardListData.text = inboxNotification.data
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        binding.root.context,
                        "Just Clicked Dashboard Notification Card Item!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToNotificationDetail(it)
                }
            }
        }
    }

    private fun navigateToNotificationDetail(view: View) {
        view.findNavController().navigate(R.id.inboxFragment)
    }

    fun dismissNotification(position: Int) {
        notifications.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, notifications.size)
    }

    private class NotificationDiffCallback : DiffUtil.ItemCallback<InboxNotification>() {

        override fun areItemsTheSame(
            oldItem: InboxNotification,
            newItem: InboxNotification
        ): Boolean {
            return oldItem.data == newItem.data
        }

        override fun areContentsTheSame(
            oldItem: InboxNotification,
            newItem: InboxNotification
        ): Boolean {
            return oldItem == newItem
        }
    }
}
