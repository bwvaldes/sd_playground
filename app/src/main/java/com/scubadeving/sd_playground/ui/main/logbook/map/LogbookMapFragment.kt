package com.scubadeving.sd_playground.ui.main.logbook.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.divelog.DiveLog
import com.scubadeving.sd_playground.data.model.divelog.DiveLog.Companion.VIEW_TYPE_MAP
import com.scubadeving.sd_playground.databinding.FragmentLogbookDivesMapBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveLogAdapter
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView

class LogbookMapFragment : Fragment() {

    private lateinit var logbookMapViewModel: LogbookMapViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logbookMapViewModel = ViewModelProvider(this).get(LogbookMapViewModel::class.java)
        return FragmentLogbookDivesMapBinding.inflate(inflater, container, false).apply {
            configureLoggedDives()
        }.root
    }

    private fun FragmentLogbookDivesMapBinding.configureLoggedDives() {
        val diveLogs: ArrayList<DiveLog> =
            arrayListOf(
                DiveLog(1, date = "January 23rd, 2020", depth = 63.toDouble(), bottomTime = 37.toDouble(), viewType = VIEW_TYPE_MAP),
                DiveLog(2, date = "February 2nd, 2020", depth = 35.toDouble(), bottomTime = 48.toDouble(), viewType = VIEW_TYPE_MAP),
                DiveLog(3, date = "February 4th, 2020", depth = 105.toDouble(), bottomTime = 29.toDouble(), viewType = VIEW_TYPE_MAP),
                DiveLog(4, date = "February 8th, 2020", depth = 120.toDouble(), bottomTime = 23.toDouble(), viewType = VIEW_TYPE_MAP),
                DiveLog(5, date = "February 10th, 2020", depth = 23.toDouble(), bottomTime = 12.toDouble(), viewType = VIEW_TYPE_MAP),
                DiveLog(6, date = "February 12th, 2020", depth = 38.2, bottomTime = 45.toDouble(), viewType = VIEW_TYPE_MAP),
                DiveLog(7, date = "February 14th, 2020", depth = 42.toDouble(), bottomTime = 38.toDouble(), viewType = VIEW_TYPE_MAP),
                DiveLog(8, date = "February 16th, 2020", depth = 45.toDouble(), bottomTime = 34.toDouble(), viewType = VIEW_TYPE_MAP),
                DiveLog(9, date = "February 18th, 2020", depth = 30.toDouble(), bottomTime = 53.toDouble(), viewType = VIEW_TYPE_MAP),
                DiveLog(10, date = "February 20th, 2020", depth = 135.toDouble(), bottomTime = 24.toDouble(), viewType = VIEW_TYPE_MAP)
            )
        parentFragment?.view?.findViewById<Toolbar>(R.id.logbook_toolbar)?.setOnClickListener {
            diveLogs.asReversed()
        }
        val adapter = DiveLogAdapter()
        adapter.submitList(diveLogs)
        logbookMapRv.configureHorizontalRecyclerView(adapter)
    }
}
