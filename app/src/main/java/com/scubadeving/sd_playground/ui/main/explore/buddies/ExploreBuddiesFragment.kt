package com.scubadeving.sd_playground.ui.main.explore.buddies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.GridLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.GridLayoutManager.VERTICAL
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

    private fun configureNearbyDivers() {
        nearby_divers_rv.apply {
            nearbyDiversLayoutManager =
                LinearLayoutManager(context, HORIZONTAL, false)
            layoutManager = nearbyDiversLayoutManager
            nearbyDiversAdapter = BuddyAdapter(nearbyDivers, true)
            adapter = nearbyDiversAdapter
            val dividerItemDecoration =
                DividerItemDecoration(context, nearbyDiversLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureDiveCenterDivers() {
        val spanCount = 2
        val spacing = 15
        val includeEdge = true
        dive_center_divers_rv.apply {
            diveCenterDiversLayoutManager =
                GridLayoutManager(context, spanCount, VERTICAL, false)
            layoutManager = diveCenterDiversLayoutManager
            diveCenterDiversAdapter = BuddyAdapter(diveCenterDivers, false)
            adapter = diveCenterDiversAdapter
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }


    private fun configurePastDivers() {
        val spanCount = 2
        val spacing = 15
        val includeEdge = true
        past_divers_rv.apply {
            pastDiversDiversLayoutManager = GridLayoutManager(context, spanCount, VERTICAL, false)
            layoutManager = pastDiversDiversLayoutManager
            pastDiversDiversAdapter = BuddyAdapter(pastDivers, false)
            adapter = pastDiversDiversAdapter
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }
}