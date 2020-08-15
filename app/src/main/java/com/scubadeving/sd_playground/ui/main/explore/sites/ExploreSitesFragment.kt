package com.scubadeving.sd_playground.ui.main.explore.sites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_explore_sites.*

class ExploreSitesFragment : Fragment() {

    private var nearbyDiveSites: List<String> = listOf("Catalina", "Ventura", "Malibu", "Anacapa")
    private lateinit var exploreSitesLayoutManager: LinearLayoutManager
    private lateinit var exploreSitesAdapter: DiveSiteAdapter
    private lateinit var exploreSitesViewModel: ExploreSitesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreSitesViewModel =
            ViewModelProvider(this).get(ExploreSitesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore_sites, container, false)
        val textView: TextView = root.findViewById(R.id.text_explore_sites)
        exploreSitesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exploreSitesLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        nearby_sites_rv.layoutManager = exploreSitesLayoutManager
        exploreSitesAdapter = DiveSiteAdapter(nearbyDiveSites)
        nearby_sites_rv.adapter = exploreSitesAdapter
        val dividerItemDecoration = DividerItemDecoration(
            nearby_sites_rv.context,
            exploreSitesLayoutManager.orientation
        )
        nearby_sites_rv.addItemDecoration(dividerItemDecoration)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(nearby_sites_rv)
    }


}