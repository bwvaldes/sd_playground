package com.scubadeving.sd_playground.ui.details.certifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Specialty
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.SpecialtyAdapter
import kotlinx.android.synthetic.main.fragment_detail_cert.*

class CertificationDetailFragment : Fragment() {

    private var prerequisites: List<String> =
        listOf(
            "Age Requirement: 12",
            "OW",
            "AOW",
            "Adventure Diver",
            "EFR Primary",
            "EFR Secondary"
        )
    private var specialties: List<Specialty> = listOf(
        Specialty("Adaptive Support"),
        Specialty("Cave"),
        Specialty("Ice"),
        Specialty("DiveMaster")
    )
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
        val root = inflater.inflate(R.layout.fragment_detail_cert, container, false)
        val textView: TextView = root.findViewById(R.id.text_cert_detail)
        certDetailViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cert_detail_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        configurePrerequisites()
        configureNextSteps()
    }

    private fun configurePrerequisites() {
        cert_detail_prerequisites_rv.apply {
            prerequisitesLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            layoutManager = prerequisitesLayoutManager
            prerequisitesAdapter = ItemDetailAdapter(prerequisites)
            adapter = prerequisitesAdapter
            val dividerItemDecoration =
                DividerItemDecoration(context, prerequisitesLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureNextSteps() {
        cert_detail_next_steps_rv.apply {
            specialtyLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            layoutManager = specialtyLayoutManager
            specialtyAdapter = SpecialtyAdapter(specialties)
            adapter = specialtyAdapter
            val dividerItemDecoration =
                DividerItemDecoration(context, prerequisitesLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }
}