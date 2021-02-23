package com.scubadeving.sd_playground.ui.main.logbook.wildlife

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
import com.scubadeving.sd_playground.data.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_logged_wildlife.logged_wildlife_rv

class LoggedWildlifeFragment : Fragment() {

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
        val wildLife: ArrayList<Wildlife> =
            arrayListOf(
                Wildlife("Garibaldi"),
                Wildlife("Halibut"),
                Wildlife("Horn Shark"),
                Wildlife("Sheephead"),
                Wildlife("Bat Ray"),
                Wildlife("Blennie"),
                Wildlife("Moray Eel")
            )
        parentFragment?.view?.findViewById<Toolbar>(R.id.logbook_toolbar)?.setOnClickListener {
            wildLife.asReversed()
        }
        logged_wildlife_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = WildlifeAdapter(wildLife)
            val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }
}
