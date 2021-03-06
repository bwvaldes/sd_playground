package com.scubadeving.sd_playground.ui.main.logbook.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.fragment_logbook_dive_log_entry.dive_log_entry_qr_scan
import kotlinx.android.synthetic.main.fragment_logbook_dive_log_entry.dive_log_entry_site_name
import kotlinx.android.synthetic.main.fragment_logbook_dive_log_entry.dive_log_entry_toolbar
import kotlinx.android.synthetic.main.fragment_logbook_dive_log_entry.dive_log_entry_wildlife_rv

class DiveLogEntryFragment : Fragment() {

    private lateinit var diveLogEntryViewModel: DiveLogEntryViewModel
    private val args: DiveLogEntryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        diveLogEntryViewModel = ViewModelProvider(this).get(DiveLogEntryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_logbook_dive_log_entry, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Log", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(android.R.drawable.ic_input_add))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dive_log_entry_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        dive_log_entry_qr_scan.setOnClickListener { findNavController().navigate(R.id.qrCodeFragment) }
        dive_log_entry_site_name.text = args.diveLogSite
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
        dive_log_entry_wildlife_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = WildlifeAdapter(wildLife)
            val dividerItemDecoration =
                DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }
}
