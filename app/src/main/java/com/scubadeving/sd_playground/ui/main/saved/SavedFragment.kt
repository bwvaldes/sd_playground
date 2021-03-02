package com.scubadeving.sd_playground.ui.main.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.SavedList
import com.scubadeving.sd_playground.data.model.sites.DiveSite
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.SavedListAdapter
import kotlinx.android.synthetic.main.fragment_saved.saved_dive_sites_rv
import kotlinx.android.synthetic.main.fragment_saved.saved_lists_rv
import kotlinx.android.synthetic.main.fragment_saved.saved_toolbar

class SavedFragment : Fragment() {

    private lateinit var savedViewModel: SavedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedViewModel =
            ViewModelProvider(this).get(SavedViewModel::class.java)
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saved_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        configureSavedListsRecyclerview()
        configureSavedDiveSitesRecyclerview()
    }

    private fun configureSavedListsRecyclerview() {
        val savedLists: ArrayList<SavedList> =
            arrayListOf(
                SavedList("Winter Dives"),
                SavedList("Training"),
                SavedList("Night Dives"),
                SavedList("Cave Dives"),
                SavedList("Ice Dives")
            )
        saved_lists_rv.adapter = SavedListAdapter(savedLists)
    }

    private fun configureSavedDiveSitesRecyclerview() {
        val savedDiveSites: List<DiveSite> = listOf(
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
        saved_dive_sites_rv.adapter = DiveSiteAdapter(savedDiveSites, false)
    }
}
