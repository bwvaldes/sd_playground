package com.scubadeving.sd_playground.ui.main.logbook.map

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
import com.scubadeving.sd_playground.ui.adapters.recyclerview.LoggedDivesAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_dive_site_detail.*
import kotlinx.android.synthetic.main.fragment_logged_dives_map.*

class LoggedDivesMapFragment : Fragment() {

    private var loggedDives: List<String> =
        listOf(
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten"
        )
    private lateinit var loggedDivesMapLayoutManager: LinearLayoutManager
    private lateinit var loggedDivesMapAdapter: LoggedDivesAdapter
    private lateinit var loggedDivesMapViewModel: LoggedDivesMapViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loggedDivesMapViewModel =
            ViewModelProvider(this).get(LoggedDivesMapViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_logged_dives_map, container, false)
        val textView: TextView = root.findViewById(R.id.text_logged_dives_map)
        loggedDivesMapViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureLoggedDives()
    }

    private fun configureLoggedDives() {
        loggedDivesMapLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        logged_dives_map_rv.layoutManager = loggedDivesMapLayoutManager
        loggedDivesMapAdapter = LoggedDivesAdapter(loggedDives)
        logged_dives_map_rv.adapter = loggedDivesMapAdapter
        val dividerItemDecoration = DividerItemDecoration(
            logged_dives_map_rv.context,
            loggedDivesMapLayoutManager.orientation
        )
        logged_dives_map_rv.addItemDecoration(dividerItemDecoration)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(logged_dives_map_rv)
    }


}