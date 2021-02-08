package com.scubadeving.sd_playground.ui.main.explore.buddies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Diver
import com.scubadeving.sd_playground.decorations.GridSpacingItemDecoration
import com.scubadeving.sd_playground.ui.adapters.recyclerview.BuddyAdapter
import kotlinx.android.synthetic.main.fragment_explore_buddies.*

class ExploreBuddiesFragment : Fragment() {

    private var nearbyDivers: List<Diver> =
        listOf(
            Diver("Bob", "Open Water", 1),
            Diver("Billy", "Rescue Diver", (0..100).random()),
            Diver("Jill", "Advanced Open Water", (0..100).random()),
            Diver("Karen", "Open Water", 0),
            Diver("Molly", "Night Diver", (0..100).random()),
            Diver("Don", "Open Water", (0..100).random()),
            Diver("Bill", "Open Water", (0..100).random()),
            Diver("Greg", "Open Water", (0..100).random())
        )

    private var diveCenterDivers: List<Diver> =
        listOf(
            Diver("Jill", "Open Water", (0..100).random()),
            Diver("Jack", "Open Water", (0..100).random()),
            Diver("Pedro", "Open Water", (0..100).random()),
            Diver("Nick", "Open Water", (0..100).random())
        )


    private var pastDivers: List<Diver> =
        listOf(
            Diver("Lia", "Discover Diver", (0..100).random()),
            Diver("Arnold", "Open Water", (0..100).random()),
            Diver("Richard", "Open Water", (0..100).random()),
            Diver("Brandon", "Open Water", (0..100).random())
        )
    private lateinit var nearbyDiversLayoutManager: LinearLayoutManager
    private lateinit var diveCenterDiversLayoutManager: GridLayoutManager
    private lateinit var pastDiversDiversLayoutManager: GridLayoutManager
    private lateinit var nearbyDiversAdapter: BuddyAdapter
    private lateinit var diveCenterDiversAdapter: BuddyAdapter
    private lateinit var pastDiversDiversAdapter: BuddyAdapter
    private lateinit var exploreBuddiesViewModel: ExploreBuddiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreBuddiesViewModel =
            ViewModelProvider(this).get(ExploreBuddiesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_explore_buddies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureNearbyDivers()
        configureDiveCenterDivers()
        configurePastDivers()
    }

    fun configureNearbyDivers() {
        nearbyDiversLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        nearby_divers_rv.layoutManager = nearbyDiversLayoutManager
        nearbyDiversAdapter = BuddyAdapter(nearbyDivers, true)
        nearby_divers_rv.adapter = nearbyDiversAdapter
        val dividerItemDecoration = DividerItemDecoration(
            nearby_divers_rv.context,
            nearbyDiversLayoutManager.orientation
        )
        nearby_divers_rv.addItemDecoration(dividerItemDecoration)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(nearby_divers_rv)
    }

    private fun configureDiveCenterDivers() {
        val spanCount = 2
        val spacing = 15
        val includeEdge = true
        diveCenterDiversLayoutManager =
            GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false)
        dive_center_divers_rv.layoutManager = diveCenterDiversLayoutManager
        diveCenterDiversAdapter = BuddyAdapter(diveCenterDivers, false)
        dive_center_divers_rv.adapter = diveCenterDiversAdapter
        dive_center_divers_rv.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount,
                spacing,
                includeEdge
            )
        )
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(dive_center_divers_rv)
    }


    private fun configurePastDivers() {
        val spanCount = 2
        val spacing = 15
        val includeEdge = true
        pastDiversDiversLayoutManager =
            GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false)
        past_divers_rv.layoutManager = pastDiversDiversLayoutManager
        pastDiversDiversAdapter = BuddyAdapter(pastDivers, false)
        past_divers_rv.adapter = pastDiversDiversAdapter
        past_divers_rv.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(past_divers_rv)
    }


}