package com.scubadeving.sd_playground.ui.main.logbook

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Wildlife
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_logbook_entry.*


class LogbookEntryFragment : Fragment() {

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
    private lateinit var logbookEntryViewModel: LogbookEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logbookEntryViewModel =
            ViewModelProvider(this).get(LogbookEntryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_logbook_entry, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Log", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(android.R.drawable.ic_input_add))
        val toolbar: androidx.appcompat.widget.Toolbar = root.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scan_qr.setOnClickListener {
            Toast.makeText(activity, "Scan QR", Toast.LENGTH_SHORT).show()
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_qr, null)
            AlertDialog.Builder(context)
                .setView(dialogView)
                .setTitle("Scan QR Code")
                .setMessage("Simply scan this QR to log a dive!")
                .setPositiveButton("Done", null)
                .show()
        }
        configureWildlife()
    }

    private fun configureWildlife() {
        wildlife_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = WildlifeAdapter(wildLife)
            val dividerItemDecoration = DividerItemDecoration(wildlife_rv.context, HORIZONTAL)
            wildlife_rv.addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(wildlife_rv)
        }
    }
}