package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.InboxNotification
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_inbox_notification_card_dashboard.view.notification_card_dashboard_clear
import kotlinx.android.synthetic.main.item_inbox_notification_card_dashboard.view.notification_card_dashboard_count
import kotlinx.android.synthetic.main.item_inbox_notification_card_dashboard.view.notification_card_dashboard_data
import kotlinx.android.synthetic.main.item_inbox_notification_card_dashboard.view.notification_card_dashboard_date
import kotlinx.android.synthetic.main.item_inbox_notification_card_list.view.notification_card_list_data
import kotlinx.android.synthetic.main.item_inbox_notification_card_list.view.notification_card_list_date

class NotificationAdapter(
    private val inboxNotifications: MutableList<InboxNotification>,
    private val orientation: Boolean
) : RecyclerView.Adapter<NotificationAdapter.NotificationsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        val inflatedView = if (orientation) {
            parent.inflate(R.layout.item_inbox_notification_card_dashboard, false)
        } else {
            parent.inflate(R.layout.item_inbox_notification_card_list, false)
        }
        return NotificationsViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = inboxNotifications.size

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        val notification = inboxNotifications[position]
        holder.bind(notification, position)
    }

    fun dismissNotification(position: Int) {
        inboxNotifications.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, inboxNotifications.size)
    }

    inner class NotificationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(inboxNotification: InboxNotification, position: Int) {
            itemView.apply {
                if (orientation) {
                    configureDashboardNotifications(position, inboxNotification)
                } else {
                    configureListNotifications(inboxNotification)
                }
                setOnClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    if (orientation) {
                        Toast.makeText(
                            itemView.context,
                            "Just Clicked Dashboard Notification Card Item!",
                            Toast.LENGTH_SHORT
                        ).show()
                        navigateToNotificationDetail(it)
                    }
                }
            }
        }

        private fun View.configureDashboardNotifications(
            position: Int,
            inboxNotification: InboxNotification
        ) {
            notification_card_dashboard_clear.setOnClickListener { dismissNotification(position) }
            notification_card_dashboard_date.text = inboxNotification.date
            notification_card_dashboard_data.text = inboxNotification.data
            notification_card_dashboard_count.text = inboxNotifications.size.toString()
        }

        private fun View.configureListNotifications(inboxNotification: InboxNotification) {
            notification_card_list_date.text = inboxNotification.date
            notification_card_list_data.text = inboxNotification.data
        }

        private fun navigateToNotificationDetail(view: View) {
            view.findNavController().navigate(R.id.inboxFragment)
        }
    }
}
