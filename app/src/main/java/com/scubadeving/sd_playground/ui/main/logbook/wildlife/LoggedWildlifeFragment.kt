package com.scubadeving.sd_playground.ui.main.logbook.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_logged_wildlife.*

class LoggedWildlifeFragment : Fragment() {

    private var wildLife: List<Wildlife> =
        listOf(
            Wildlife("Garibaldi"),
            Wildlife("Halibut"),
            Wildlife("Horn Shark"),
            Wildlife("Sheephead"),
            Wildlife("Bat Ray"),
            Wildlife("Blennie"),
            Wildlife("Moray Eel")
        )
    private lateinit var loggedWildlifeViewModel: LoggedWildlifeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loggedWildlifeViewModel =
            ViewModelProvider(this).get(LoggedWildlifeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_logged_wildlife, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureWildlife()
    }

    private fun configureWildlife() {
        logged_wildlife_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = WildlifeAdapter(wildLife)
            val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
            logged_wildlife_rv.addItemDecoration(dividerItemDecoration)
        }
    }
}