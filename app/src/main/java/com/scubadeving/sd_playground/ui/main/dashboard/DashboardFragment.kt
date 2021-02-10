package com.scubadeving.sd_playground.ui.main.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveSite
import com.scubadeving.sd_playground.data.Notification
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.NotificationsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_detail_notifications.*
import kotlinx.android.synthetic.main.view_dashboard_items.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Search Dashboard", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        val profileIcon = root.findViewById<ImageView>(R.id.profile_icon)
        navToProfile(profileIcon)
        val notificationsIcon = root.findViewById<ImageView>(R.id.notifications_icon)
        navToNotifications(notificationsIcon)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUpcomingDivesRecyclerView()
        configureDashboardNotificationsRecyclerView()
        configureDashItems()
    }

    private fun configureUpcomingDivesRecyclerView() {
        val diveSites: List<DiveSite> = listOf(
            DiveSite("Shaw's Cove", "Newport Beach", 4.5, 112),
            DiveSite("Casino Point", "Catalina", 3.2, 14),
            DiveSite("Se lion Point", "Ventura", 4.8, 86),
            DiveSite("Leo Carillo", "Malibu", 4.75, 42),
            DiveSite("Boat Dive 1", "Anacapa", 3.98, 8)
        )
        upcoming_dives_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = DiveSiteAdapter(diveSites)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureDashItems() {
        dash_weather_card.setOnClickListener {
            it.findNavController().navigate(R.id.weatherDetailFragment)
        }
        dash_fly_card.setOnClickListener {
            // TODO: Nav to most recently logged dive
            it.findNavController().navigate(R.id.navigation_logbook)
        }
        dash_maintenance_card.setOnClickListener {
            // TODO: Use safeArgs to hit gear fragment
            it.findNavController().navigate(R.id.profileFragment)
        }
        dash_next_card.setOnClickListener {
            it.findNavController().navigate(R.id.certDetailFragment)
        }
    }

    private fun configureDashboardNotificationsRecyclerView() {
        val notifications: ArrayList<Notification> = arrayListOf(
            Notification("Today", "This is a Notification"),
            Notification("Feb 3rd", "This is a Notification"),
            Notification("Jan 30th", "This is a Notification"),
            Notification("Jan 18th", "This is a Notification"),
            Notification("Dec 20th", "This is a Notification"),
            Notification("Dec 3rd", "This is a Notification")
        )
        dashboard_notifications_rv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = NotificationsAdapter(notifications, true)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun navToProfile(view: View) {
        view.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_profileFragment)
        }
    }

    private fun navToNotifications(view: View) {
        view.setOnClickListener {
            it.findNavController().navigate(R.id.notificationsFragment)
        }
    }
}