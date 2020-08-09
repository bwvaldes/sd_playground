package com.scubadeving.sd_playground.ui.dashboard

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.SpecialtyAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cert_detail.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private var diveSites: List<String> = listOf("Catalina", "Ventura", "Malibu", "Anacapa")
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
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Search Dashboard", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        val profileIcon = root.findViewById<ImageView>(R.id.profile_icon)
        navToProfile(profileIcon)
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
    }

    fun navToProfile(view: View) {
        view.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_profileFragment)
        }
    }
}