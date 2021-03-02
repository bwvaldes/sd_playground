package com.scubadeving.sd_playground.ui.main.explore.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.ExploreFilter
import com.scubadeving.sd_playground.data.model.wildlife.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ExploreFilterAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.GridSpacingItemDecoration
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView
import kotlinx.android.synthetic.main.fragment_explore_wildlife.explore_wildlife_filter_rv
import kotlinx.android.synthetic.main.fragment_explore_wildlife.explore_wildlife_nearby_rv

class ExploreWildlifeFragment : Fragment() {

    private lateinit var exploreWildlifeViewModel: ExploreWildlifeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreWildlifeViewModel =
            ViewModelProvider(this).get(ExploreWildlifeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_explore_wildlife, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureExploreWildlifeFilter()
        configureExploreWildlifeNearby()
    }

    private fun configureExploreWildlifeFilter() {
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
        explore_wildlife_filter_rv.apply {
            adapter = ExploreFilterAdapter(filters)
            addItemDecoration(GridSpacingItemDecoration())
        }
    }

    private fun configureExploreWildlifeNearby() {
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
        explore_wildlife_nearby_rv.configureHorizontalRecyclerView(WildlifeAdapter(nearbyWildlife) as Adapter<ViewHolder>)
    }
}
