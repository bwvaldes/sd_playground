package com.scubadeving.sd_playground.ui.details.divesite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
import kotlinx.android.synthetic.main.fragment_detail_dive_site.dive_site_detail_conditions_rv
import kotlinx.android.synthetic.main.fragment_detail_dive_site.dive_site_detail_dive_centers_rv
import kotlinx.android.synthetic.main.fragment_detail_dive_site.dive_site_detail_toolbar
import kotlinx.android.synthetic.main.fragment_detail_dive_site.dive_site_detail_toolbar_layout
import kotlinx.android.synthetic.main.fragment_detail_dive_site.logbook_entry_wildlife_rv

class DiveSiteDetailFragment : Fragment() {

    private lateinit var diveSiteDetailViewModel: DiveSiteDetailViewModel
    private val args: DiveSiteDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        diveSiteDetailViewModel = ViewModelProvider(this).get(DiveSiteDetailViewModel::class.java)
        return inflater.inflate(R.layout.fragment_detail_dive_site, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dive_site_detail_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        dive_site_detail_toolbar_layout.title = args.diveSiteName
        configureConditions()
        configureDiveCenters()
        configureWildlife()
    }

    private fun configureConditions() {
        val conditions: List<String> =
            listOf(
                "Difficulty: 5/10",
                "65 degrees fahrenheit",
                "Depth: 9m",
                "Visibility: 15-30ft",
                "Dive Type: Shore"
            )
        dive_site_detail_conditions_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = ItemDetailAdapter(conditions)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureDiveCenters() {
        val diveCenters: List<DiveCenter> =
            listOf(
                DiveCenter("Newport Divers"),
                DiveCenter("Eco Dive Center")
            )
        dive_site_detail_dive_centers_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = DiveCenterAdapter(diveCenters)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureWildlife() {
        val wildLife: ArrayList<Wildlife> =
            arrayListOf(
                Wildlife("Garibaldi"),
                Wildlife("Halibut"),
                Wildlife("Horn Shark"),
                Wildlife("Sheephead"),
                Wildlife("Bat Ray"),
                Wildlife("Blennie"),
                Wildlife("Moray Eel")
            )
        logbook_entry_wildlife_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = WildlifeAdapter(wildLife)
            val dividerItemDecoration =
                DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }
}
