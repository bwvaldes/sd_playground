package com.scubadeving.sd_playground.ui.main.logbook.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.divelog.DiveLog
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveLogAdapter
import kotlinx.android.synthetic.main.fragment_logbook_dives_map.logbook_map_rv

class LogbookMapFragment : Fragment() {

    private lateinit var logbookMapViewModel: LogbookMapViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logbookMapViewModel = ViewModelProvider(this).get(LogbookMapViewModel::class.java)
        return inflater.inflate(R.layout.fragment_logbook_dives_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureLoggedDives()
    }

    private fun configureLoggedDives() {
        val diveLogs: ArrayList<DiveLog> =
            arrayListOf(
                DiveLog(1, date = "January 23rd, 2020", depth = 63.toDouble(), bottomTime = 37.toDouble()),
                DiveLog(2, date = "February 2nd, 2020", depth = 35.toDouble(), bottomTime = 48.toDouble()),
                DiveLog(3, date = "February 4th, 2020", depth = 105.toDouble(), bottomTime = 29.toDouble()),
                DiveLog(4, date = "February 8th, 2020", depth = 120.toDouble(), bottomTime = 23.toDouble()),
                DiveLog(5, date = "February 10th, 2020", depth = 23.toDouble(), bottomTime = 12.toDouble()),
                DiveLog(6, date = "February 12th, 2020", depth = 38.2, bottomTime = 45.toDouble()),
                DiveLog(7, date = "February 14th, 2020", depth = 42.toDouble(), bottomTime = 38.toDouble()),
                DiveLog(8, date = "February 16th, 2020", depth = 45.toDouble(), bottomTime = 34.toDouble()),
                DiveLog(9, date = "February 18th, 2020", depth = 30.toDouble(), bottomTime = 53.toDouble()),
                DiveLog(10, date = "February 20th, 2020", depth = 135.toDouble(), bottomTime = 24.toDouble())
            )
        parentFragment?.view?.findViewById<Toolbar>(R.id.logbook_toolbar)?.setOnClickListener {
            diveLogs.asReversed()
        }
        logbook_map_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = DiveLogAdapter(diveLogs, false)
            val dividerItemDecoration = DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }
}
