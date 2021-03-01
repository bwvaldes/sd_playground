package com.scubadeving.sd_playground.ui.main.explore.sites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.ExploreFilter
import com.scubadeving.sd_playground.data.sites.DiveSite
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ExploreFilterAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_explore_sites.explore_sites_all_rv
import kotlinx.android.synthetic.main.fragment_explore_sites.explore_sites_filter_rv
import kotlinx.android.synthetic.main.fragment_explore_sites.explore_sites_nearby_rv

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
        configureExploreSitesFilter()
    }

    private fun configureExploreSitesFilter() {
        val filters: List<ExploreFilter> =
            listOf(
                ExploreFilter("Tropical"),
                ExploreFilter("Ice"),
                ExploreFilter("Wreck"),
                ExploreFilter("Deep"),
                ExploreFilter("Cave"),
                ExploreFilter("Nitrox"),
                ExploreFilter("Abroad"),
                ExploreFilter("Shallow"),
                ExploreFilter("Night"),
                ExploreFilter("Brackish")
            )
        val spanCount = 2
        val spacing = 15
        val includeEdge = true
        explore_sites_filter_rv.apply {
            layoutManager = GridLayoutManager(context, spanCount, HORIZONTAL, false)
            adapter = ExploreFilterAdapter(filters)
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        }
    }

    private fun configureExploreNearbyDiveSites() {
        val nearbyDiveSites: List<DiveSite> = listOf(
            DiveSite("Casino Point", rating = 3.2, reviews = 14),
            DiveSite("Leo Carillo", rating = 4.75, reviews = 42),
            DiveSite("Boat Dive 1", rating = 3.98, reviews = 8)
        )
        explore_sites_nearby_rv.apply {
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
            DiveSite("Casino Point", rating = 3.2, reviews = 14),
            DiveSite("Leo Carillo", rating = 4.75, reviews = 42),
            DiveSite("Boat Dive 1", rating = 3.98, reviews = 8),
            DiveSite("Casino Point", rating = 3.2, reviews = 14),
            DiveSite("Leo Carillo", rating = 4.75, reviews = 42),
            DiveSite("Boat Dive 1", rating = 3.98, reviews = 8),
            DiveSite("Casino Point", rating = 3.2, reviews = 14),
            DiveSite("Leo Carillo", rating = 4.75, reviews = 42),
            DiveSite("Boat Dive 1", rating = 3.98, reviews = 8)
        )
        explore_sites_all_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = DiveSiteAdapter(allDiveSites, false)
        }
    }
}
