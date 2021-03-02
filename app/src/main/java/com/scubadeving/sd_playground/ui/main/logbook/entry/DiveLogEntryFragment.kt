package com.scubadeving.sd_playground.ui.main.logbook.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.wildlife.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView
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
        return inflater.inflate(R.layout.fragment_logbook_dive_log_entry, container, false)
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
        dive_log_entry_wildlife_rv.configureHorizontalRecyclerView(WildlifeAdapter(wildLife) as Adapter<ViewHolder>)
    }
}
