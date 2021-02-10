package com.scubadeving.sd_playground.ui.main.explore.sites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveSite
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import kotlinx.android.synthetic.main.fragment_explore_sites.*

class ExploreSitesFragment : Fragment() {

    private lateinit var exploreSitesViewModel: ExploreSitesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreSitesViewModel =
            ViewModelProvider(this).get(ExploreSitesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_explore_sites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureExploreNearbyDiveSites()
        configureExploreAllDiveSites()
        explore_tropical.setOnClickListener { findNavController().navigate(R.id.exploreSitesFilteredFragment) }
    }

    private fun configureExploreNearbyDiveSites() {
        val nearbyDiveSites: List<DiveSite> = listOf(
            DiveSite("Casino Point", "Catalina", 3.2, 14),
            DiveSite("Leo Carillo", "Malibu", 4.75, 42),
            DiveSite("Boat Dive 1", "Anacapa", 3.98, 8)
        )
        nearby_sites_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = DiveSiteAdapter(nearbyDiveSites, true)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureExploreAllDiveSites() {
        val allDiveSites: List<DiveSite> = listOf(
            DiveSite("Casino Point", "Catalina", 3.2, 14),
            DiveSite("Leo Carillo", "Malibu", 4.75, 42),
            DiveSite("Boat Dive 1", "Anacapa", 3.98, 8),
            DiveSite("Casino Point", "Catalina", 3.2, 14),
            DiveSite("Leo Carillo", "Malibu", 4.75, 42),
            DiveSite("Boat Dive 1", "Anacapa", 3.98, 8),
            DiveSite("Casino Point", "Catalina", 3.2, 14),
            DiveSite("Leo Carillo", "Malibu", 4.75, 42),
            DiveSite("Boat Dive 1", "Anacapa", 3.98, 8)
        )
        all_sites_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = DiveSiteAdapter(allDiveSites, false)
        }
    }
}