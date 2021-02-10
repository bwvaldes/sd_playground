package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Notification
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_notification_card_dashboard.view.*
import kotlinx.android.synthetic.main.item_notification_card_list.view.*

class NotificationsAdapter(
    private val notifications: MutableList<Notification>,
    private val orientation: Boolean
) : RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        val inflatedView = if (orientation) {
            parent.inflate(R.layout.item_notification_card_dashboard, false)
        } else {
            parent.inflate(R.layout.item_notification_card_list, false)
        }
        return NotificationsViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = notifications.size

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        val notification = notifications[position]
        holder.bind(notification, position)
    }

    fun dismissNotification(position: Int) {
        notifications.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, notifications.size)
    }

    inner class NotificationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            if (orientation) {
                Toast.makeText(
                    itemView.context,
                    "Just Clicked Dashboard Notification Card Item!",
                    Toast.LENGTH_SHORT
                ).show()
                view.findNavController().navigate(R.id.notificationsFragment)
            }
        }

        fun bind(notification: Notification, position: Int) {
            itemView.apply {
                if (orientation) {
                    configureDashboardNotifications(position, notification)
                } else {
                    configureListNotifications(notification)
                }
            }
        }

        private fun View.configureDashboardNotifications(
            position: Int,
            notification: Notification
        ) {
            notification_card_dashboard_clear.setOnClickListener { dismissNotification(position) }
            notification_card_dashboard_date.text = notification.date
            notification_card_dashboard_data.text = notification.data
        }

        private fun View.configureListNotifications(notification: Notification) {
            notification_card_list_date.text = notification.date
            notification_card_list_data.text = notification.data
        }
    }
}
