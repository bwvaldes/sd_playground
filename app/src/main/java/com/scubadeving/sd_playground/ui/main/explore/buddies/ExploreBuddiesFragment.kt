package com.scubadeving.sd_playground.ui.main.explore.buddies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.GridLayoutManager.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Diver
import com.scubadeving.sd_playground.ui.adapters.recyclerview.BuddyAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_explore_buddies.dive_center_divers_rv
import kotlinx.android.synthetic.main.fragment_explore_buddies.dive_center_divers_see_all
import kotlinx.android.synthetic.main.fragment_explore_buddies.nearby_divers_rv
import kotlinx.android.synthetic.main.fragment_explore_buddies.past_divers_rv
import kotlinx.android.synthetic.main.fragment_explore_buddies.past_divers_see_all

class ExploreBuddiesFragment : Fragment() {

    private lateinit var exploreBuddiesViewModel: ExploreBuddiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreBuddiesViewModel = ViewModelProvider(this).get(ExploreBuddiesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_explore_buddies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureNearbyDivers()
        configureDiveCenterDivers()
        configurePastDivers()
    }

    private fun configureNearbyDivers() {
        val nearbyDivers: ArrayList<Diver> =
            arrayListOf(
                Diver("Bob", "Open Water", 1),
                Diver("Billy", "Rescue Diver", (0..100).random()),
                Diver("Jill", "Advanced Open Water", (0..100).random()),
                Diver("Karen", "Open Water", 0),
                Diver("Molly", "Night Diver", (0..100).random()),
                Diver("Don", "Open Water", (0..100).random()),
                Diver("Bill", "Open Water", (0..100).random()),
                Diver("Greg", "Open Water", (0..100).random())
            )
        nearby_divers_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = BuddyAdapter(nearbyDivers, true)
            val dividerItemDecoration =
                DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureDiveCenterDivers() {
        val diveCenterDivers: ArrayList<Diver> =
            arrayListOf(
                Diver("Jill", "Open Water", (0..100).random()),
                Diver("Jack", "Open Water", (0..100).random()),
                Diver("Pedro", "Open Water", (0..100).random()),
                Diver("Nick", "Open Water", (0..100).random()),
                Diver("Jill", "Advanced Open Water", (0..100).random()),
                Diver("Karen", "Open Water", 0),
                Diver("Molly", "Night Diver", (0..100).random())
            )
        val spanCount = 2
        val spacing = 15
        val includeEdge = true
        dive_center_divers_rv.apply {
            layoutManager = GridLayoutManager(context, spanCount, VERTICAL, false)
            adapter = BuddyAdapter(diveCenterDivers, false)
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
        dive_center_divers_see_all.setOnClickListener {
            Toast.makeText(context, "Just Clicked Dive Center Buddies!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configurePastDivers() {
        val pastDivers: ArrayList<Diver> =
            arrayListOf(
                Diver("Lia", "Discover Diver", (0..100).random()),
                Diver("Arnold", "Open Water", (0..100).random()),
                Diver("Richard", "Open Water", (0..100).random()),
                Diver("Brandon", "Open Water", (0..100).random()),
                Diver("Jill", "Open Water", (0..100).random()),
                Diver("Jack", "Open Water", (0..100).random()),
                Diver("Pedro", "Open Water", (0..100).random()),
                Diver("Nick", "Open Water", (0..100).random())
            )
        val spanCount = 2
        val spacing = 15
        val includeEdge = true
        past_divers_rv.apply {
            layoutManager = GridLayoutManager(context, spanCount, VERTICAL, false)
            adapter = BuddyAdapter(pastDivers, false)
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
        past_divers_see_all.setOnClickListener {
            Toast.makeText(context, "Just Clicked Past Dive Buddies!", Toast.LENGTH_SHORT).show()
        }
    }
}
