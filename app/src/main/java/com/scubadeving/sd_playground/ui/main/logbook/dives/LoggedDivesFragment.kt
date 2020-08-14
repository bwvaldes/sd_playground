package com.scubadeving.sd_playground.ui.main.logbook.dives

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
import kotlinx.android.synthetic.main.fragment_logged_dives.*
import kotlinx.android.synthetic.main.fragment_logged_dives_map.*

class LoggedDivesFragment : Fragment() {

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
    private lateinit var loggedDivesLayoutManager: LinearLayoutManager
    private lateinit var loggedDivesAdapter: LoggedDivesAdapter
    private lateinit var loggedDivesViewModel: LoggedDivesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loggedDivesViewModel =
            ViewModelProvider(this).get(LoggedDivesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_logged_dives, container, false)
        val textView: TextView = root.findViewById(R.id.text_logged_dives)
        loggedDivesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureLoggedDives()
    }

    private fun configureLoggedDives() {
        loggedDivesLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        logged_dives_rv.layoutManager = loggedDivesLayoutManager
        loggedDivesAdapter = LoggedDivesAdapter(loggedDives)
        logged_dives_rv.adapter = loggedDivesAdapter
        val dividerItemDecoration = DividerItemDecoration(
            logged_dives_rv.context,
            loggedDivesLayoutManager.orientation
        )
        logged_dives_rv.addItemDecoration(dividerItemDecoration)
    }


}