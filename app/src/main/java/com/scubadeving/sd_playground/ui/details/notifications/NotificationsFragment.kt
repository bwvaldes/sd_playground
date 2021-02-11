package com.scubadeving.sd_playground.ui.details.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Notification
import com.scubadeving.sd_playground.ui.adapters.recyclerview.NotificationAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail_notifications.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_detail_notifications, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Gear", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(android.R.drawable.ic_input_add))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notifications_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        configureNotificationsRecyclerView()
    }

    private fun configureNotificationsRecyclerView() {
        val notifications: ArrayList<Notification> = arrayListOf(
            Notification("Today", "This is a Notification"),
            Notification("Feb 3rd", "This is a Notification"),
            Notification("Jan 30th", "This is a Notification"),
            Notification("Jan 18th", "This is a Notification"),
            Notification("Dec 20th", "This is a Notification"),
            Notification("Dec 3rd", "This is a Notification")
        )
        notifications_rv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = NotificationAdapter(notifications, false)
            val swipeHandler = object : SwipeToDeleteCallback(context) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = adapter as NotificationAdapter
                    adapter.dismissNotification(viewHolder.adapterPosition)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }
}
