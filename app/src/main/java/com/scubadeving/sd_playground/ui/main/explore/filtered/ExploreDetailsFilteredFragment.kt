package com.scubadeving.sd_playground.ui.main.explore.filtered

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.scubadeving.sd_playground.data.model.sites.DiveSite
import com.scubadeving.sd_playground.data.model.wildlife.Wildlife
import com.scubadeving.sd_playground.databinding.FragmentExploreFilteredDetailsBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter

class ExploreDetailsFilteredFragment : Fragment() {

    private lateinit var exploreDetailsFilteredViewModel: ExploreDetailsFilteredViewModel
    private val args: ExploreDetailsFilteredFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreDetailsFilteredViewModel =
            ViewModelProvider(this).get(ExploreDetailsFilteredViewModel::class.java)
        return FragmentExploreFilteredDetailsBinding.inflate(inflater, container, false).apply {
            exploreDetailsFilteredToolbar.apply {
                setNavigationOnClickListener { findNavController().navigateUp() }
                title = args.exploreDetailName
            }
            if (args.isWildlife) {
                configureExploreFilteredWildlife()
            } else configureExploreFilteredDiveSites()
        }.root
    }

    private fun FragmentExploreFilteredDetailsBinding.configureExploreFilteredDiveSites() {
        val filteredDiveSites: List<DiveSite> = listOf(
            DiveSite("Casino Point", rating = 3.2, reviews = 14),
            DiveSite("Leo Carillo", rating = 4.75, reviews = 42),
            DiveSite("Boat Dive 1", rating = 3.98, reviews = 8),
            DiveSite("Casino Point", rating = 3.2, reviews = 14),
            DiveSite("Leo Carillo", rating = 4.75, reviews = 42),
            DiveSite("Boat Dive 1", rating = 3.98, reviews = 8),
            DiveSite("Casino Point", rating = 3.2, reviews = 14),
            DiveSite("Leo Carillo", rating = 4.75, reviews = 42),
            DiveSite("Boat Dive 1", rating = 3.98, reviews = 8)
        )
        exploreDetailsFilteredRv.adapter = DiveSiteAdapter(filteredDiveSites, false)
    }

    private fun FragmentExploreFilteredDetailsBinding.configureExploreFilteredWildlife() {
        val filteredWildlife: ArrayList<Wildlife> =
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
        adapter.submitList(filteredWildlife)
        exploreDetailsFilteredRv.adapter = adapter
    }
}
