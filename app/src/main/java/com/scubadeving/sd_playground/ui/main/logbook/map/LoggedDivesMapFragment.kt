package com.scubadeving.sd_playground.ui.main.logbook.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveLog
import com.scubadeving.sd_playground.ui.adapters.recyclerview.LoggedDivesAdapter
import kotlinx.android.synthetic.main.fragment_logged_dives_map.*

class LoggedDivesMapFragment : Fragment() {

    private var loggedDives: List<DiveLog> =
        listOf(
            DiveLog(1, "Shaw's Cove", "January 23rd, 2020", 3.8, "63ft", "37min"),
            DiveLog(2, "Catalina", "February 2nd, 2020", 4.2, "35ft", "48min"),
            DiveLog(3, "Leo Carillo", "February 4th, 2020", 4.3, "105ft", "29min"),
            DiveLog(4, "Anacapa", "February 8th, 2020", 5.0, "120ft", "23min"),
            DiveLog(5, "Key West", "February 10th, 2020", 4.89, "23ft", "1hr 2min"),
            DiveLog(6, "Casino Point Park", "February 12th, 2020", 3.62, "38.2ft", "45min"),
            DiveLog(7, "Grouper Grove", "February 14th, 2020", 4.0, "42ft", "38min"),
            DiveLog(8, "Slippery Isles", "February 16th, 2020", 3.8, "45ft", "34min"),
            DiveLog(9, "Ventura", "February 18th, 2020", 4.98, "30ft", "53min"),
            DiveLog(10, "Pointe Dume", "February 20th, 2020", 4.0, "135ft", "24min")
        )
    private lateinit var loggedDivesMapViewModel: LoggedDivesMapViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loggedDivesMapViewModel =
            ViewModelProvider(this).get(LoggedDivesMapViewModel::class.java)
        return inflater.inflate(R.layout.fragment_logged_dives_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureLoggedDives()
    }

    private fun configureLoggedDives() {
        logged_dives_map_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = LoggedDivesAdapter(loggedDives, false)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            logged_dives_map_rv.addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(logged_dives_map_rv)
        }
    }
}