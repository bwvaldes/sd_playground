package com.scubadeving.sd_playground.ui.main.explore.sites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.data.model.ExploreFilter
import com.scubadeving.sd_playground.data.model.sites.DiveSite
import com.scubadeving.sd_playground.databinding.FragmentExploreSitesBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ExploreFilterAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.GridSpacingItemDecoration
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView

class ExploreSitesFragment : Fragment() {

    private lateinit var exploreSitesViewModel: ExploreSitesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreSitesViewModel = ViewModelProvider(this).get(ExploreSitesViewModel::class.java)
        return FragmentExploreSitesBinding.inflate(inflater, container, false).apply {
            configureExploreNearbyDiveSites()
            configureExploreAllDiveSites()
            configureExploreSitesFilter()
        }.root
    }

    private fun FragmentExploreSitesBinding.configureExploreSitesFilter() {
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
        val targetAdapter = ExploreFilterAdapter()
        targetAdapter.submitList(filters)
        exploreSitesFilterRv.apply {
            adapter = targetAdapter
            addItemDecoration(GridSpacingItemDecoration())
        }
    }

    private fun FragmentExploreSitesBinding.configureExploreNearbyDiveSites() {
        val nearbyDiveSites: List<DiveSite> = listOf(
            DiveSite("Casino Point", rating = 3.2, reviews = 14),
            DiveSite("Leo Carillo", rating = 4.75, reviews = 42),
            DiveSite("Boat Dive 1", rating = 3.98, reviews = 8)
        )
        val adapter = DiveSiteAdapter()
        adapter.submitList(nearbyDiveSites)
        exploreSitesNearbyRv.configureHorizontalRecyclerView(adapter)
    }

    private fun FragmentExploreSitesBinding.configureExploreAllDiveSites() {
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
        val adapter = DiveSiteAdapter()
        adapter.submitList(allDiveSites)
        exploreSitesAllRv.adapter = adapter
    }
}
