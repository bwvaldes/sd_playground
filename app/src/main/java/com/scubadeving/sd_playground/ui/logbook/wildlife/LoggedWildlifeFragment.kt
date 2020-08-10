package com.scubadeving.sd_playground.ui.logbook.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveCenterAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dive_site_detail.*
import kotlinx.android.synthetic.main.fragment_logged_wildlife.*

class LoggedWildlifeFragment : Fragment() {

    private var wildLife: List<String> =
        listOf(
            "Garibaldi",
            "Halibut",
            "Horn Shark",
            "Sheephead",
            "Bat Ray",
            "Blennies",
            "Moray Eel"
        )
    private lateinit var loggedWildlifeLayoutManager: LinearLayoutManager
    private lateinit var loggedWildlifeAdapter: WildlifeAdapter
    private lateinit var loggedWildlifeViewModel: LoggedWildlifeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loggedWildlifeViewModel =
            ViewModelProvider(this).get(LoggedWildlifeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_logged_wildlife, container, false)
        val textView: TextView = root.findViewById(R.id.text_logged_wildlife)
        loggedWildlifeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureWildlife()
    }

    private fun configureWildlife() {
        loggedWildlifeLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        logged_wildlife_rv.layoutManager = loggedWildlifeLayoutManager
        loggedWildlifeAdapter = WildlifeAdapter(wildLife)
        logged_wildlife_rv.adapter = loggedWildlifeAdapter
        val dividerItemDecoration = DividerItemDecoration(
            logged_wildlife_rv.context,
            loggedWildlifeLayoutManager.orientation
        )
        logged_wildlife_rv.addItemDecoration(dividerItemDecoration)
    }


}