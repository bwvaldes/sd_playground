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
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.certification.EligibilityStatus
import com.scubadeving.sd_playground.data.model.certification.Specialty
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.SpecialtyAdapter
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView
import kotlinx.android.synthetic.main.fragment_certification_detail.cert_detail_next_steps_rv
import kotlinx.android.synthetic.main.fragment_certification_detail.cert_detail_prerequisites_rv
import kotlinx.android.synthetic.main.fragment_certification_detail.cert_detail_status
import kotlinx.android.synthetic.main.fragment_certification_detail.cert_detail_toolbar
import kotlinx.android.synthetic.main.fragment_certification_detail.cert_detail_toolbar_layout

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
        return inflater.inflate(R.layout.fragment_certification_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cert_detail_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        configurePrerequisites()
        configureNextSteps()
        cert_detail_toolbar_layout.title = args.certificationName
        if (args.certificationName == "Advanced Open Water Diver") {
            cert_detail_status.apply {
                text = EligibilityStatus.COMPLETED.name
                setBackgroundColor(Color.GREEN)
            }
        }
    }

    private fun configurePrerequisites() {
        val prerequisites: List<String> =
            listOf(
                "Age Requirement: 12",
                "OW",
                "AOW",
                "Adventure Diver",
                "EFR Primary",
                "EFR Secondary"
            )
        cert_detail_prerequisites_rv.configureHorizontalRecyclerView(ItemDetailAdapter(prerequisites) as Adapter<ViewHolder>)
    }

    private fun configureNextSteps() {
        val specialties: List<Specialty> = listOf(
            Specialty("Adaptive Support"),
            Specialty("Cave"),
            Specialty("Ice"),
            Specialty("DiveMaster")
        )
        cert_detail_next_steps_rv.configureHorizontalRecyclerView(SpecialtyAdapter(specialties) as Adapter<ViewHolder>)
    }
}
