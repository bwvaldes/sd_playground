package com.scubadeving.sd_playground.ui.main.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.InboxNotification
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.data.model.sites.DiveSite
import com.scubadeving.sd_playground.data.source.repository.DiverRepository
import com.scubadeving.sd_playground.databinding.FragmentDashboardBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.NotificationAdapter

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels {
        DashboardViewModelFactory(DiverRepository(FirebaseFirestore.getInstance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return FragmentDashboardBinding.inflate(inflater, container, false).apply {
            subscribeUi(this)
        }.root
    }

    private fun subscribeUi(binding: FragmentDashboardBinding) {
        binding.apply {
            dashboardViewModel.currentDiver.observe(
                viewLifecycleOwner,
                Observer {
                    diver = it
                    welcomeCertLevel.text = diver?.certifications?.last()?.certificationName
                }
            )
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
                        navigateToProfile(this, diver!!)
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
            DiveSite("Shaw's Cove", rating = 4.5, reviews = 112),
            DiveSite("Casino Point", rating = 3.2, reviews = 14),
            DiveSite("Se lion Point", rating = 4.8, reviews = 86),
            DiveSite("Leo Carillo", rating = 4.75, reviews = 42),
            DiveSite("Boat Dive 1", rating = 3.98, reviews = 8)
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
            dashMaintenanceCard.setOnClickListener { navigateToProfile(it, diver!!) }
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
        val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.firstName!!)
        view.findNavController().navigate(directions)
    }

    private fun navigateToInbox(view: View) {
        view.findNavController().navigate(R.id.inboxFragment)
    }

    private fun navigateToCertificationDetail(view: View) {
        val directions = MainNavigationDirections.actionGlobalCertDetailFragment("")
        view.findNavController().navigate(directions)
    }
}
