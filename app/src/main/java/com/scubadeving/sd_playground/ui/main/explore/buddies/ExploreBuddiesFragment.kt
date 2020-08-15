package com.scubadeving.sd_playground.ui.main.explore.buddies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.decorations.GridSpacingItemDecoration
import com.scubadeving.sd_playground.ui.adapters.recyclerview.BuddyAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.SpecialtyAdapter
import kotlinx.android.synthetic.main.fragment_detail_cert.*
import kotlinx.android.synthetic.main.fragment_explore_buddies.*
import kotlinx.android.synthetic.main.fragment_profile_certifications.*

class ExploreBuddiesFragment : Fragment() {

    private var nearbyDivers: List<String> =
        listOf(
            "Bob",
            "Billy",
            "Jill",
            "Karen",
            "Molly",
            "Don",
            "Bill",
            "Greg"
        )

    private var diveCenterDivers: List<String> =
        listOf(
            "Karen",
            "Molly",
            "Chad",
            "Stuart"
        )


    private var pastDivers: List<String> =
        listOf(
            "Mike",
            "Don",
            "Bill",
            "Greg"
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
        val root = inflater.inflate(R.layout.fragment_explore_buddies, container, false)
        val textView: TextView = root.findViewById(R.id.text_explore_buddies)
        exploreBuddiesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
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