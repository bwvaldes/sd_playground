package com.scubadeving.sd_playground.ui.details.certifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.SpecialtyAdapter
import kotlinx.android.synthetic.main.fragment_cert_detail.*

class CertDetailFragment : Fragment() {

    private var prerequisites: List<String> =
        listOf(
            "Age Requirement: 12",
            "OW",
            "AOW",
            "Adventure Diver",
            "EFR Primary",
            "EFR Secondary"
        )
    private var certCards: List<String> = listOf("Adaptive Support", "Cave", "Ice", "DiveMaster")
    private lateinit var prerequisitesLayoutManager: LinearLayoutManager
    private lateinit var specialtyLayoutManager: LinearLayoutManager
    private lateinit var prerequisitesAdapter: ItemDetailAdapter
    private lateinit var specialtyAdapter: SpecialtyAdapter
    private lateinit var certDetailViewModel: CertDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        certDetailViewModel = ViewModelProvider(this).get(CertDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cert_detail, container, false)
        val textView: TextView = root.findViewById(R.id.text_cert_detail)
        certDetailViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val toolbar: androidx.appcompat.widget.Toolbar = root.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurePrerequisites()
        configureNextSteps()
    }

    private fun configurePrerequisites() {
        prerequisitesLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        prerequisites_rv.layoutManager = prerequisitesLayoutManager
        prerequisitesAdapter = ItemDetailAdapter(prerequisites)
        prerequisites_rv.adapter = prerequisitesAdapter
        val dividerItemDecoration = DividerItemDecoration(
            prerequisites_rv.context,
            prerequisitesLayoutManager.orientation
        )
        prerequisites_rv.addItemDecoration(dividerItemDecoration)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(prerequisites_rv)
    }

    private fun configureNextSteps() {
        specialtyLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        next_steps_rv.layoutManager = specialtyLayoutManager
        specialtyAdapter = SpecialtyAdapter(certCards)
        next_steps_rv.adapter = specialtyAdapter
        val dividerItemDecoration = DividerItemDecoration(
            next_steps_rv.context,
            prerequisitesLayoutManager.orientation
        )
        next_steps_rv.addItemDecoration(dividerItemDecoration)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(next_steps_rv)
    }
}