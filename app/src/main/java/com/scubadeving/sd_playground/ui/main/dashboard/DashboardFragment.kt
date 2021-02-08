package com.scubadeving.sd_playground.ui.main.dashboard

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveSite
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.view_dashboard_items.*

class DashboardFragment : Fragment() {

    private var diveSites: List<DiveSite> = listOf(
        DiveSite("Shaw's Cove", "Newport Beach", 4.5, 112),
        DiveSite("Casino Point", "Catalina", 3.2, 14),
        DiveSite("Se lion Point", "Ventura", 4.8, 86),
        DiveSite("Leo Carillo", "Malibu", 4.75, 42),
        DiveSite("Boat Dive 1", "Anacapa", 3.98, 8)
    )
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DiveSiteAdapter

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
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        upcoming_dives_rv.layoutManager = layoutManager
        adapter = DiveSiteAdapter(diveSites)
        upcoming_dives_rv.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(
            upcoming_dives_rv.context,
            layoutManager.orientation
        )
        upcoming_dives_rv.addItemDecoration(dividerItemDecoration)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(upcoming_dives_rv)
        configureDashItems()
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