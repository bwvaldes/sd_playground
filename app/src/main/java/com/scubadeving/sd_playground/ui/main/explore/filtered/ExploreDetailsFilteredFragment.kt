package com.scubadeving.sd_playground.ui.main.explore.filtered

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveSite
import com.scubadeving.sd_playground.data.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_explore_details_filtered.*

class ExploreDetailsFilteredFragment : Fragment() {

    private lateinit var exploreDetailsFilteredViewModel: ExploreDetailsFilteredViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreDetailsFilteredViewModel =
            ViewModelProvider(this).get(ExploreDetailsFilteredViewModel::class.java)
        return inflater.inflate(R.layout.fragment_explore_details_filtered, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        explore_details_filtered_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        if (arguments?.isEmpty == true) {
            configureExploreFilteredWildlife()
        } else configureExploreFilteredDiveSites()
    }

    private fun configureExploreFilteredDiveSites() {
        val filteredDiveSites: List<DiveSite> = listOf(
            DiveSite("Casino Point", "Catalina", 3.2, 14),
            DiveSite("Leo Carillo", "Malibu", 4.75, 42),
            DiveSite("Boat Dive 1", "Anacapa", 3.98, 8),
            DiveSite("Casino Point", "Catalina", 3.2, 14),
            DiveSite("Leo Carillo", "Malibu", 4.75, 42),
            DiveSite("Boat Dive 1", "Anacapa", 3.98, 8),
            DiveSite("Casino Point", "Catalina", 3.2, 14),
            DiveSite("Leo Carillo", "Malibu", 4.75, 42),
            DiveSite("Boat Dive 1", "Anacapa", 3.98, 8)
        )
        explore_details_filtered_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = DiveSiteAdapter(filteredDiveSites, false)
        }
    }

    private fun configureExploreFilteredWildlife() {
        val filteredWildlife: List<Wildlife> =
            listOf(
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