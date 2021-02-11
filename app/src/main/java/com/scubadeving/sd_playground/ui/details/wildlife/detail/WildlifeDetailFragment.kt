package com.scubadeving.sd_playground.ui.details.wildlife.detail

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
import com.scubadeving.sd_playground.data.ConservationStatus
import com.scubadeving.sd_playground.data.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_detail_wildlife_details.*

class WildlifeDetailFragment : Fragment() {

    private lateinit var conditionsLayoutManager: LinearLayoutManager
    private lateinit var wildlifeLayoutManager: LinearLayoutManager
    private lateinit var conditionsAdapter: ItemDetailAdapter
    private lateinit var wildlifeDetailAdapter: WildlifeAdapter
    private lateinit var wildlifeDetailViewModel: WildlifeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeDetailViewModel = ViewModelProvider(this).get(WildlifeDetailViewModel::class.java)
        return inflater.inflate(R.layout.fragment_detail_wildlife_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wildlife_detail_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        configureEncounters()
        configureWildlife()
    }

    private fun configureEncounters() {
        val encounters: List<String> =
            listOf(
                "Thailand:2",
                "Phillipines:14"
            )
        wildlife_detail_encounters_rv.apply {
            conditionsLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            layoutManager = conditionsLayoutManager
            conditionsAdapter = ItemDetailAdapter(encounters)
            adapter = conditionsAdapter
            val dividerItemDecoration = DividerItemDecoration(context, conditionsLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureWildlife() {
        val wildLife: List<Wildlife> =
            listOf(
                Wildlife(
                    "Whale Shark",
                    "Rhincodon typus",
                    ConservationStatus.VULNERABLE,
                    "Gentle Giant",
                    "Phillipines"
                ),
                Wildlife(
                    "Sunfish",
                    "Mola-Mola",
                    ConservationStatus.ENDANGERED,
                    "Heavy Bony fishy",
                    "Oregon"
                ),
                Wildlife(
                    "Spotted Sting Ray",
                    "Taeniura lymma",
                    ConservationStatus.VULNERABLE,
                    "Poisonous looking Ray",
                    "Australia"
                ),
                Wildlife(
                    "Remora",
                    "Echeneidae",
                    ConservationStatus.LEAST_CONCERN,
                    "Symbiotic Fish",
                    "Dubai"
                )
            )
        wildlife_detail_nearby_rv.apply {
            wildlifeLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            layoutManager = wildlifeLayoutManager
            wildlifeDetailAdapter = WildlifeAdapter(wildLife)
            adapter = wildlifeDetailAdapter
            val dividerItemDecoration = DividerItemDecoration(context, wildlifeLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }
}