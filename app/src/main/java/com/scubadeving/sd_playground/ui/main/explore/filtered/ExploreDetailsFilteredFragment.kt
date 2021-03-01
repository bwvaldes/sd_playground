package com.scubadeving.sd_playground.ui.main.explore.filtered

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.sites.DiveSite
import com.scubadeving.sd_playground.data.model.wildlife.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_explore_filtered_details.explore_details_filtered_rv
import kotlinx.android.synthetic.main.fragment_explore_filtered_details.explore_details_filtered_toolbar

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
        return inflater.inflate(R.layout.fragment_explore_filtered_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        explore_details_filtered_toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            title = args.exploreDetailName
        }
        if (args.isWildlife) {
            configureExploreFilteredWildlife()
        } else configureExploreFilteredDiveSites()
    }

    private fun configureExploreFilteredDiveSites() {
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
        explore_details_filtered_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = DiveSiteAdapter(filteredDiveSites, false)
        }
    }

    private fun configureExploreFilteredWildlife() {
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
        explore_details_filtered_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = WildlifeAdapter(filteredWildlife)
        }
    }
}
