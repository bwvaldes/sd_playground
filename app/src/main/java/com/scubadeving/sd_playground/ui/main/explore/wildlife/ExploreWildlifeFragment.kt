package com.scubadeving.sd_playground.ui.main.explore.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_explore_sites.*
import kotlinx.android.synthetic.main.fragment_explore_wildlife.*

class ExploreWildlifeFragment : Fragment() {

    private var nearbyWildlife: List<String> =
        listOf(
            "Garibaldi",
            "Halibut",
            "Horn Shark",
            "Sheephead",
            "Bat Ray",
            "Blennies",
            "Moray Eel"
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
        val root = inflater.inflate(R.layout.fragment_explore_wildlife, container, false)
        val textView: TextView = root.findViewById(R.id.text_explore_wildlife)
        exploreWildlifeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exploreWildlifeLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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