package com.scubadeving.sd_playground.ui.details.divesite

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
import com.scubadeving.sd_playground.data.model.DiveCenter
import com.scubadeving.sd_playground.data.model.wildlife.Wildlife
import com.scubadeving.sd_playground.databinding.FragmentDiveSiteDetailsBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveCenterAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView

class DiveSiteDetailFragment : Fragment() {

    private lateinit var diveSiteDetailViewModel: DiveSiteDetailViewModel
    private val args: DiveSiteDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        diveSiteDetailViewModel = ViewModelProvider(this).get(DiveSiteDetailViewModel::class.java)
        return FragmentDiveSiteDetailsBinding.inflate(inflater, container, false).apply {
            diveSiteDetailToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            diveSiteDetailToolbarLayout.title = args.diveSiteName
            configureConditions()
            configureDiveCenters()
            configureWildlife()
        }.root
    }

    private fun FragmentDiveSiteDetailsBinding.configureConditions() {
        val conditions: List<String> =
            listOf(
                "Difficulty: 5/10",
                "65 degrees fahrenheit",
                "Depth: 9m",
                "Visibility: 15-30ft",
                "Dive Type: Shore"
            )
        diveSiteDetailConditionsRv.configureHorizontalRecyclerView(ItemDetailAdapter(conditions) as Adapter<ViewHolder>)
    }

    private fun FragmentDiveSiteDetailsBinding.configureDiveCenters() {
        val diveCenters: List<DiveCenter> =
            listOf(
                DiveCenter("Newport Divers"),
                DiveCenter("Eco Dive Center")
            )
        diveSiteDetailDiveCentersRv.configureHorizontalRecyclerView(DiveCenterAdapter(diveCenters) as Adapter<ViewHolder>)
    }

    private fun FragmentDiveSiteDetailsBinding.configureWildlife() {
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
        val adapter = WildlifeAdapter()
        adapter.submitList(wildLife)
        diveSiteDetailWildlifeRv.configureHorizontalRecyclerView(adapter)
    }
}
