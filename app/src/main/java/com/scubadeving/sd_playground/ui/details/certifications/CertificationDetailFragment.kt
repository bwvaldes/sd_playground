package com.scubadeving.sd_playground.ui.details.certifications

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.scubadeving.sd_playground.data.model.certification.EligibilityStatus
import com.scubadeving.sd_playground.data.model.certification.Specialty
import com.scubadeving.sd_playground.databinding.FragmentCertificationDetailBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.SpecialtyAdapter
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView

class CertificationDetailFragment : Fragment() {

    private lateinit var certificationDetailViewModel: CertificationDetailViewModel
    private val args: CertificationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        certificationDetailViewModel =
            ViewModelProvider(this).get(CertificationDetailViewModel::class.java)
        return FragmentCertificationDetailBinding.inflate(inflater, container, false).apply {
            certDetailToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            certDetailToolbarLayout.title = args.certificationName
            configurePrerequisites()
            configureNextSteps()
            if (args.certificationName == "Advanced Open Water Diver") {
                certDetailStatus.apply {
                    text = EligibilityStatus.COMPLETED.name
                    setBackgroundColor(Color.GREEN)
                }
            }
        }.root
    }

    private fun FragmentCertificationDetailBinding.configurePrerequisites() {
        val prerequisites: List<String> =
            listOf(
                "Age Requirement: 12",
                "OW",
                "AOW",
                "Adventure Diver",
                "EFR Primary",
                "EFR Secondary"
            )
        val adapter = ItemDetailAdapter()
        adapter.submitList(prerequisites)
        certDetailPrerequisitesRv.configureHorizontalRecyclerView(adapter)
    }

    private fun FragmentCertificationDetailBinding.configureNextSteps() {
        val specialties: List<Specialty> = listOf(
            Specialty("Adaptive Support"),
            Specialty("Cave"),
            Specialty("Ice"),
            Specialty("DiveMaster")
        )
        val adapter = SpecialtyAdapter()
        adapter.submitList(specialties)
        certDetailNextStepsRv.configureHorizontalRecyclerView(adapter)
    }
}
