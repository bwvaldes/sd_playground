package com.scubadeving.sd_playground.ui.main.logbook.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.wildlife.Wildlife
import com.scubadeving.sd_playground.databinding.FragmentLogbookWildlifeBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter

class LogbookWildlifeFragment : Fragment() {

    private lateinit var logbookWildlifeViewModel: LogbookWildlifeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logbookWildlifeViewModel = ViewModelProvider(this).get(LogbookWildlifeViewModel::class.java)
        return FragmentLogbookWildlifeBinding.inflate(inflater, container, false).apply {
            configureWildlife()
        }.root
    }

    private fun FragmentLogbookWildlifeBinding.configureWildlife() {
        val wildLife: ArrayList<Wildlife> =
            arrayListOf(
                Wildlife(commonName = "Garibaldi"),
                Wildlife(commonName = "Halibut"),
                Wildlife(commonName = "Horn Shark"),
                Wildlife(commonName = "Sheephead"),
                Wildlife(commonName = "Bat Ray"),
                Wildlife(commonName = "Blennie"),
                Wildlife(commonName = "Moray Eel")
            )
        parentFragment?.view?.findViewById<Toolbar>(R.id.logbook_toolbar)?.setOnClickListener {
            wildLife.asReversed()
        }
        val wildlifeAdapter = WildlifeAdapter()
        wildlifeAdapter.submitList(wildLife)
        loggedWildlifeRv.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = wildlifeAdapter
            val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }
}
