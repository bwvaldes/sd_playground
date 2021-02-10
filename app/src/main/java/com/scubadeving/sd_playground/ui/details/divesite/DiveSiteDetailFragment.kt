package com.scubadeving.sd_playground.ui.details.divesite

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
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveCenter
import com.scubadeving.sd_playground.data.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveCenterAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_detail_dive_site.*

class DiveSiteDetailFragment : Fragment() {

    private var conditions: List<String> =
        listOf(
            "Difficulty: 5/10",
            "65 degrees fahrenheit",
            "Depth: 9m",
            "Visibility: 15-30ft",
            "Dive Type: Shore"
        )
    private var diveCenters: List<DiveCenter> =
        listOf(
            DiveCenter("Newport Divers"),
            DiveCenter("Eco Dive Center")
        )
    private var wildLife: List<Wildlife> =
        listOf(
            Wildlife("Garibaldi"),
            Wildlife("Halibut"),
            Wildlife("Horn Shark"),
            Wildlife("Sheephead"),
            Wildlife("Bat Ray"),
            Wildlife("Blennie"),
            Wildlife("Moray Eel")
        )
    private lateinit var conditionsLayoutManager: LinearLayoutManager
    private lateinit var diveCentersLayoutManager: LinearLayoutManager
    private lateinit var wildlifeLayoutManager: LinearLayoutManager
    private lateinit var conditionsAdapter: ItemDetailAdapter
    private lateinit var diveCentersAdapter: DiveCenterAdapter
    private lateinit var wildlifeAdapter: WildlifeAdapter
    private lateinit var diveSiteDetailViewModel: DiveSiteDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        diveSiteDetailViewModel = ViewModelProvider(this).get(DiveSiteDetailViewModel::class.java)
        return inflater.inflate(R.layout.fragment_detail_dive_site, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dive_site_detail_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        configureConditions()
        configureDiveCenters()
        configureWildlife()
    }

    private fun configureConditions() {
        dive_site_detail_conditions_rv.apply {
            conditionsLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            layoutManager = conditionsLayoutManager
            conditionsAdapter = ItemDetailAdapter(conditions)
            adapter = conditionsAdapter
            val dividerItemDecoration = DividerItemDecoration(context, conditionsLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureDiveCenters() {
        dive_site_detail_dive_centers_rv.apply {
            diveCentersLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            layoutManager = diveCentersLayoutManager
            diveCentersAdapter = DiveCenterAdapter(diveCenters)
            adapter = diveCentersAdapter
            val dividerItemDecoration = DividerItemDecoration(dive_site_detail_dive_centers_rv.context, diveCentersLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureWildlife() {
        logbook_entry_wildlife_rv.apply {
            wildlifeLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            layoutManager = wildlifeLayoutManager
            wildlifeAdapter = WildlifeAdapter(wildLife)
            adapter = wildlifeAdapter
            val dividerItemDecoration = DividerItemDecoration(context, wildlifeLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }
}