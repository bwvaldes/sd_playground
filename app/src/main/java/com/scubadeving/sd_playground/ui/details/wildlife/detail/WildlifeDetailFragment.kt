package com.scubadeving.sd_playground.ui.details.wildlife.detail

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
import com.scubadeving.sd_playground.data.ConservationStatus
import com.scubadeving.sd_playground.data.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_wildlife_details.wildlife_detail_conservation_status
import kotlinx.android.synthetic.main.fragment_wildlife_details.wildlife_detail_encounters_rv
import kotlinx.android.synthetic.main.fragment_wildlife_details.wildlife_detail_focus
import kotlinx.android.synthetic.main.fragment_wildlife_details.wildlife_detail_nearby_rv
import kotlinx.android.synthetic.main.fragment_wildlife_details.wildlife_detail_toolbar
import kotlinx.android.synthetic.main.fragment_wildlife_details.wildlife_detail_toolbar_layout

class WildlifeDetailFragment : Fragment() {

    private lateinit var wildlifeDetailViewModel: WildlifeDetailViewModel
    private val args: WildlifeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeDetailViewModel = ViewModelProvider(this).get(WildlifeDetailViewModel::class.java)
        return inflater.inflate(R.layout.fragment_wildlife_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wildlife_detail_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        wildlife_detail_toolbar_layout.title = args.wildlifeName
        configureEncounters()
        configureWildlife()
        if (args.wildlifeName == "Megalodon") {
            wildlife_detail_focus.apply {
                isEnabled = false
                setBackgroundResource(R.drawable.ic_action_focus_unavailable)
            }
            wildlife_detail_conservation_status.text = "Red List Status:\n${ConservationStatus.EXTINCT.name}"
        }
    }

    private fun configureEncounters() {
        val encounters: List<String> =
            listOf(
                "Thailand:2",
                "Phillipines:14"
            )
        wildlife_detail_encounters_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = ItemDetailAdapter(encounters)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureWildlife() {
        val wildLife: ArrayList<Wildlife> =
            arrayListOf(
                Wildlife(
                    "Megalodon",
                    "Megalodon",
                    ConservationStatus.EXTINCT,
                    "Largest Shark",
                    "Pacific Ocean"
                ),
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
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = WildlifeAdapter(wildLife)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }
}
