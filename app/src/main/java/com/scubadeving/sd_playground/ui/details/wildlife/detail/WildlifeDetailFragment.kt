package com.scubadeving.sd_playground.ui.details.wildlife.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.wildlife.ConservationStatus
import com.scubadeving.sd_playground.data.model.wildlife.Wildlife
import com.scubadeving.sd_playground.databinding.FragmentWildlifeDetailsBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView

class WildlifeDetailFragment : Fragment() {

    private lateinit var wildlifeDetailViewModel: WildlifeDetailViewModel
    private val args: WildlifeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeDetailViewModel = ViewModelProvider(this).get(WildlifeDetailViewModel::class.java)
        return FragmentWildlifeDetailsBinding.inflate(inflater, container, false).apply {
            wildlifeDetailToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            wildlifeDetailToolbarLayout.title = args.wildlifeName
            configureEncounters()
            configureWildlife()
            if (args.wildlifeName == "Megalodon") {
                wildlifeDetailFocus.apply {
                    isEnabled = false
                    setBackgroundResource(R.drawable.ic_action_focus_unavailable)
                }
                wildlifeDetailConservationStatus.text = "Red List Status:\n${ConservationStatus.EXTINCT.name}"
            }
        }.root
    }

    private fun FragmentWildlifeDetailsBinding.configureEncounters() {
        val encounters: List<String> =
            listOf(
                "Thailand:2",
                "Phillipines:14"
            )
        wildlifeDetailEncountersRv.configureHorizontalRecyclerView(ItemDetailAdapter(encounters) as Adapter<ViewHolder>)
    }

    private fun FragmentWildlifeDetailsBinding.configureWildlife() {
        val wildLife: ArrayList<Wildlife> =
            arrayListOf(
                Wildlife(
                    "",
                    "Megalodon",
                    "Megalodon",
                    ConservationStatus.EXTINCT,
                    "Largest Shark"
                ),
                Wildlife(
                    "",
                    "Whale Shark",
                    "Rhincodon typus",
                    ConservationStatus.VULNERABLE,
                    "Gentle Giant"
                ),
                Wildlife(
                    "",
                    "Sunfish",
                    "Mola-Mola",
                    ConservationStatus.ENDANGERED,
                    "Heavy Bony fishy"
                ),
                Wildlife(
                    "",
                    "Spotted Sting Ray",
                    "Taeniura lymma",
                    ConservationStatus.VULNERABLE,
                    "Poisonous looking Ray"
                ),
                Wildlife(
                    "",
                    "Remora",
                    "Echeneidae",
                    ConservationStatus.LEAST_CONCERN,
                    "Symbiotic Fish"
                )
            )
        val adapter = WildlifeAdapter()
        adapter.submitList(wildLife)
        wildlifeDetailNearbyRv.configureHorizontalRecyclerView(adapter)
    }
}
