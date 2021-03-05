package com.scubadeving.sd_playground.ui.main.inbox.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.data.model.InboxNotification
import com.scubadeving.sd_playground.data.model.InboxNotification.Companion.NOTIFICATION_TYPE_INBOX
import com.scubadeving.sd_playground.databinding.FragmentInboxNotificationsBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.NotificationAdapter

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        return FragmentInboxNotificationsBinding.inflate(inflater, container, false).apply {
            configureNotificationsRecyclerView()
        }.root
    }

    private fun FragmentInboxNotificationsBinding.configureNotificationsRecyclerView() {
        val inboxNotifications: ArrayList<InboxNotification> = arrayListOf(
            InboxNotification("Today", "This is a Notification", NOTIFICATION_TYPE_INBOX),
            InboxNotification("Feb 3rd", "This is a Notification", NOTIFICATION_TYPE_INBOX),
            InboxNotification("Jan 30th", "This is a Notification", NOTIFICATION_TYPE_INBOX),
            InboxNotification("Jan 18th", "This is a Notification", NOTIFICATION_TYPE_INBOX),
            InboxNotification("Dec 20th", "This is a Notification", NOTIFICATION_TYPE_INBOX),
            InboxNotification("Dec 3rd", "This is a Notification", NOTIFICATION_TYPE_INBOX)
        )
        val adapter = NotificationAdapter()
        adapter.submitList(inboxNotifications)
        notificationsRv.adapter = adapter
    }
}
