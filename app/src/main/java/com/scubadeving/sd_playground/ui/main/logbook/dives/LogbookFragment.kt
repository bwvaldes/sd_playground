package com.scubadeving.sd_playground.ui.main.logbook.dives

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.divelog.DiveLog
import com.scubadeving.sd_playground.data.sites.DiveSite
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveLogAdapter
import kotlinx.android.synthetic.main.fragment_logbook_dives.logbook_rv

class LogbookFragment : Fragment() {

    private lateinit var logbookViewModel: LogbookViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logbookViewModel =
            ViewModelProvider(this).get(LogbookViewModel::class.java)
        return inflater.inflate(R.layout.fragment_logbook_dives, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureLoggedDives()
    }

    private fun configureLoggedDives() {
        val diveLogs: ArrayList<DiveLog> =
            arrayListOf(
                DiveLog(1, date ="January 23rd, 2020", depth ="63ft".toDouble(), bottomTime = "37min".toDouble()),
                DiveLog(2,  date ="February 2nd, 2020", depth ="35ft".toDouble(), bottomTime ="48min".toDouble()),
                DiveLog(3,  date ="February 4th, 2020",depth = "105ft".toDouble(),bottomTime = "29min".toDouble()),
                DiveLog(4,  date ="February 8th, 2020",depth = "120ft".toDouble(),bottomTime = "23min".toDouble()),
                DiveLog(5, date = "February 10th, 2020", depth ="23ft".toDouble(), bottomTime ="1hr 2min".toDouble()),
                DiveLog(6, date = "February 12th, 2020",depth = "38.2ft".toDouble(), bottomTime ="45min".toDouble()),
                DiveLog(7, date = "February 14th, 2020", depth ="42ft".toDouble(),bottomTime = "38min".toDouble()),
                DiveLog(8,  date ="February 16th, 2020", depth ="45ft".toDouble(), bottomTime ="34min".toDouble()),
                DiveLog(9, date = "February 18th, 2020", depth = "30ft".toDouble(),bottomTime = "53min".toDouble()),
                DiveLog(10,  date ="February 20th, 2020", depth ="135ft".toDouble(), bottomTime ="24min".toDouble())
            )
        parentFragment?.view?.findViewById<Toolbar>(R.id.logbook_toolbar)?.setOnClickListener {
            diveLogs.asReversed()
        }
        logbook_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = DiveLogAdapter(diveLogs, true)
            val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }
}
