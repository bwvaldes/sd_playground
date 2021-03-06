package com.scubadeving.sd_playground.ui.main.logbook.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.wildlife.Wildlife
import com.scubadeving.sd_playground.databinding.FragmentLogbookDiveLogEntryBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView

class DiveLogEntryFragment : Fragment() {

    private lateinit var diveLogEntryViewModel: DiveLogEntryViewModel
    private val args: DiveLogEntryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        diveLogEntryViewModel = ViewModelProvider(this).get(DiveLogEntryViewModel::class.java)
        return FragmentLogbookDiveLogEntryBinding.inflate(inflater, container, false).apply {
            diveLogEntryToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            diveLogEntryQrScan.setOnClickListener { findNavController().navigate(R.id.qrCodeFragment) }
            diveLogEntrySiteName.text = args.diveLogSite
            configureWildlife()
        }.root
    }

    private fun FragmentLogbookDiveLogEntryBinding.configureWildlife() {
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
        val adapter = WildlifeAdapter()
        adapter.submitList(wildLife)
        diveLogEntryWildlifeRv.configureHorizontalRecyclerView(adapter)
    }
}
