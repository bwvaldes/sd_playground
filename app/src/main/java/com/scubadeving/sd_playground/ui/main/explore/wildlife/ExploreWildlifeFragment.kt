package com.scubadeving.sd_playground.ui.main.explore.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.data.model.ExploreFilter
import com.scubadeving.sd_playground.data.model.wildlife.Wildlife
import com.scubadeving.sd_playground.databinding.FragmentExploreWildlifeBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ExploreFilterAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.GridSpacingItemDecoration
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView

class ExploreWildlifeFragment : Fragment() {

    private lateinit var exploreWildlifeViewModel: ExploreWildlifeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreWildlifeViewModel = ViewModelProvider(this).get(ExploreWildlifeViewModel::class.java)
        return FragmentExploreWildlifeBinding.inflate(inflater, container, false).apply {
            configureExploreWildlifeFilter()
            configureExploreWildlifeNearby()
        }.root
    }

    private fun FragmentExploreWildlifeBinding.configureExploreWildlifeFilter() {
        val filters: List<ExploreFilter> =
            listOf(
                ExploreFilter("Mammals", true),
                ExploreFilter("Birds", true),
                ExploreFilter("Brackish", true),
                ExploreFilter("Night Dwellers", true),
                ExploreFilter("Micro", true),
                ExploreFilter("Deep", true),
                ExploreFilter("Turtles", true),
                ExploreFilter("Sharks", true),
                ExploreFilter("Rays", true),
                ExploreFilter("Reef", true)
            )
        val targetAdapter = ExploreFilterAdapter()
        targetAdapter.submitList(filters)
        exploreWildlifeFilterRv.apply {
            adapter = targetAdapter
            addItemDecoration(GridSpacingItemDecoration())
        }
    }

    private fun FragmentExploreWildlifeBinding.configureExploreWildlifeNearby() {
        val nearbyWildlife: ArrayList<Wildlife> =
            arrayListOf(
                Wildlife("Garibaldi"),
                Wildlife("Halibut"),
                Wildlife("Horn Shark"),
                Wildlife("Sheephead"),
                Wildlife("Bat Ray"),
                Wildlife("Blennie"),
                Wildlife("Moray Eel")
            )
        val adapter = WildlifeAdapter()
        adapter.submitList(nearbyWildlife)
        exploreWildlifeNearbyRv.configureHorizontalRecyclerView(adapter)
    }
}
