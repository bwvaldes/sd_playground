package com.scubadeving.sd_playground.ui.main.explore.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_explore_wildlife.*

class ExploreWildlifeFragment : Fragment() {

    private var nearbyWildlife: List<Wildlife> =
        listOf(
            Wildlife("Garibaldi"),
            Wildlife("Halibut"),
            Wildlife("Horn Shark"),
            Wildlife("Sheephead"),
            Wildlife("Bat Ray"),
            Wildlife("Blennie"),
            Wildlife("Moray Eel")
        )
    private lateinit var exploreWildlifeLayoutManager: LinearLayoutManager
    private lateinit var exploreWildlifeAdapter: WildlifeAdapter
    private lateinit var exploreWildlifeViewModel: ExploreWildlifeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreWildlifeViewModel =
            ViewModelProvider(this).get(ExploreWildlifeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_explore_wildlife, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exploreWildlifeLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        nearby_wildlife_rv.layoutManager = exploreWildlifeLayoutManager
        exploreWildlifeAdapter = WildlifeAdapter(nearbyWildlife)
        nearby_wildlife_rv.adapter = exploreWildlifeAdapter
        val dividerItemDecoration = DividerItemDecoration(
            nearby_wildlife_rv.context,
            exploreWildlifeLayoutManager.orientation
        )
        nearby_wildlife_rv.addItemDecoration(dividerItemDecoration)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(nearby_wildlife_rv)
    }


}