package com.scubadeving.sd_playground.ui.main.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Certification
import com.scubadeving.sd_playground.data.DiveSite
import com.scubadeving.sd_playground.data.Diver
import com.scubadeving.sd_playground.data.InboxNotification
import com.scubadeving.sd_playground.databinding.FragmentDashboardBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.NotificationAdapter
import kotlinx.android.synthetic.main.activity_main.fab

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private val currentUser = Diver("Brian Valdes", "Advanced Open Water Diver", 2)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        return FragmentDashboardBinding.inflate(inflater, container, false).apply {
            activity?.fab?.setOnClickListener {
                Toast.makeText(activity, "Search Dashboard", Toast.LENGTH_SHORT).show()
            }
            activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_action_search))
            subscribeUi(this)
        }.root
    }

    private fun subscribeUi(binding: FragmentDashboardBinding) {
        binding.apply {
            diver = currentUser
            configureToolbar()
            configureUpcomingDivesRecyclerView()
            configureDashboardNotificationsRecyclerView()
            configureDashItems()
        }
    }

    private fun FragmentDashboardBinding.configureToolbar() {
        dashboardToolbar.apply {
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_profile -> {
                        navigateToProfile(this, currentUser)
                        true
                    }
                    R.id.action_saved -> {
                        navigateToSaved(this)
                        true
                    }
                    R.id.action_inbox -> {
                        navigateToInbox(this)
                        true
                    }
                    else -> true
                }
            }
        }
    }

    private fun FragmentDashboardBinding.configureUpcomingDivesRecyclerView() {
        val diveSites: List<DiveSite> = listOf(
            DiveSite("Shaw's Cove", "Newport Beach", 4.5, 112),
            DiveSite("Casino Point", "Catalina", 3.2, 14),
            DiveSite("Se lion Point", "Ventura", 4.8, 86),
            DiveSite("Leo Carillo", "Malibu", 4.75, 42),
            DiveSite("Boat Dive 1", "Anacapa", 3.98, 8)
        )
        upcomingDivesRv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = DiveSiteAdapter(diveSites)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun FragmentDashboardBinding.configureDashItems() {
        welcomeDash.apply {
            dashWeatherCard.setOnClickListener { navigateToWeatherDetail(it) }
            dashFlyCard.setOnClickListener { navigateToDiveLogEntry(it) }
            dashMaintenanceCard.setOnClickListener { navigateToProfile(it, currentUser) }
            dashNextCard.setOnClickListener { navigateToCertificationDetail(it) }
        }
    }

    private fun FragmentDashboardBinding.configureDashboardNotificationsRecyclerView() {
        val inboxNotifications: ArrayList<InboxNotification> = arrayListOf(
            InboxNotification("Today", "This is a Notification"),
            InboxNotification("Feb 3rd", "This is a Notification"),
            InboxNotification("Jan 30th", "This is a Notification"),
            InboxNotification("Jan 18th", "This is a Notification"),
            InboxNotification("Dec 20th", "This is a Notification"),
            InboxNotification("Dec 3rd", "This is a Notification")
        )
        dashboardNotificationsRv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = NotificationAdapter(inboxNotifications, true)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun navigateToDiveLogEntry(view: View) {
        view.findNavController().navigate(R.id.navigation_logbook)
    }

    private fun navigateToWeatherDetail(view: View) {
        view.findNavController().navigate(R.id.weatherDetailFragment)
    }

    private fun navigateToSaved(view: View) {
        view.findNavController().navigate(R.id.savedFragment)
    }

    private fun navigateToProfile(view: View, diver: Diver) {
        val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.name)
        view.findNavController().navigate(directions)
    }

    private fun navigateToInbox(view: View) {
        view.findNavController().navigate(R.id.inboxFragment)
    }

    private fun navigateToCertificationDetail(view: View) {
        val nextCertification = Certification("Rescue Diver")
        val directions = MainNavigationDirections.actionGlobalCertDetailFragment(nextCertification.name)
        view.findNavController().navigate(directions)
    }
}
