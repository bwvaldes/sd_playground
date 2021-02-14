package com.scubadeving.sd_playground.ui.main.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveSite
import com.scubadeving.sd_playground.data.SavedList
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.SavedListAdapter
import kotlinx.android.synthetic.main.fragment_saved.*

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
                SavedList("Night Dives")
            )
        saved_lists_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = SavedListAdapter(savedLists)
        }
    }

    private fun configureSavedDiveSitesRecyclerview() {
        val savedDiveSites: List<DiveSite> = listOf(
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
        saved_dive_sites_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = DiveSiteAdapter(savedDiveSites, false)
        }
    }
}