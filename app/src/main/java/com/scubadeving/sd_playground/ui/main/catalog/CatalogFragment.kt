package com.scubadeving.sd_playground.ui.main.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.google.android.material.chip.Chip
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.certification.CatalogCertification
import com.scubadeving.sd_playground.data.model.certification.Specialty
import com.scubadeving.sd_playground.databinding.FragmentCertificationCatalogBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertificationAdapter
import kotlinx.android.synthetic.main.activity_main.fab

class CatalogFragment : Fragment() {

    private val beginnerSpecialties: List<Specialty> = listOf(
        Specialty("AWARE - Coral Reef Conservation"),
        Specialty("Project AWARE Specialist"),
        Specialty("Digital Underwater Photographer (for Snorkelers)")
    )
    private val intermediateSpecialties: List<Specialty> = listOf(
        Specialty("Advanced Rebreather Diver"),
        Specialty("Altitude Diver"),
        Specialty("AWARE - Fish Identification"),
        Specialty("AWARE Shark Conservation"),
        Specialty("Boat Diver"),
        Specialty("Digital Underwater Photographer"),
        Specialty("Diver Propulsion Vehicle Diver"),
        Specialty("Drift Diver"),
        Specialty("Dry Suit Diver"),
        Specialty("Emergency Oxygen Provider"),
        Specialty("Enriched Air Diver"),
        Specialty("Equipment Specialist"),
        Specialty("Multilevel Diver"),
        Specialty("Night Diver"),
        Specialty("Peak Performance Buoyancy"),
        Specialty("Rebreather Diver"),
        Specialty("Sidemount Diver"),
        Specialty("Underwater Naturalist"),
        Specialty("Underwater Navigator"),
        Specialty("Underwater Photographer"),
        Specialty("Underwater Videographer")
    )
    private val adventureSpecialties: List<Specialty> = listOf(
        Specialty("Deep Diver"),
        Specialty("Wreck Diver")
    )
    private val advancedSpecialties: List<Specialty> = listOf(
        Specialty("Cavern Diver"),
        Specialty("Ice Diver"),
        Specialty("Search and Recovery Diver"),
        Specialty("Semiclosed Rebreather - Dolphin/Atlantis"),
        Specialty("Scuba Review")
    )
    private val emergencySpecialties: List<Specialty> = listOf(
        Specialty("Emergency First Response Provider"),
        Specialty("Emergency First Response Instructor"),
        Specialty("Emergency First Response Instructor Trainer")
    )
    private val rescueSpecialties: List<Specialty> = listOf(
        Specialty("Divemaster"),
        Specialty("Open Water Scuba Instructor"),
        Specialty("Assistant Instructor"),
        Specialty("Specialty Instructor"),
        Specialty("Master Scuba Diver Trainer"),
        Specialty("ICF Staff Instructor"),
        Specialty("Master Instructor"),
        Specialty("Course Director")
    )
    private val padiCatalogCertifications: List<CatalogCertification> = listOf(
        CatalogCertification("Discover Snorkeling", specialties = listOf(Specialty("Discover Snorkeling"))),
        CatalogCertification("Seal Team", specialties = beginnerSpecialties),
        CatalogCertification("Bubble Maker", specialties = beginnerSpecialties),
        CatalogCertification("Discover Scuba Diving", specialties = beginnerSpecialties),
        CatalogCertification("Skin Diver", specialties = beginnerSpecialties),
        CatalogCertification("Open Water Diver", specialties = intermediateSpecialties),
        CatalogCertification("Scuba Diver", specialties = intermediateSpecialties),
        CatalogCertification("Adventure Diver", specialties = adventureSpecialties),
        CatalogCertification("Advanced Open Water Diver", specialties = advancedSpecialties.plus(emergencySpecialties)),
        CatalogCertification("Rescue Diver", specialties = rescueSpecialties),
        CatalogCertification("Master Scuba Diver", specialties = listOf(Specialty("Master Scuba Diver")))
    )

    private val sdiIntermediateSpecialties: List<Specialty> = listOf(
        Specialty("Advanced Adventure"),
        Specialty("Advanced Buoyancy"),
        Specialty("Altitude"),
        Specialty("Boat")
    )

    private val sdiCatalogCertifications: List<CatalogCertification> = listOf(
        CatalogCertification("Open Water Scuba Diver", specialties = sdiIntermediateSpecialties),
        CatalogCertification("Advanced Diver", specialties = sdiIntermediateSpecialties),
        CatalogCertification("Rescue Diver", specialties = sdiIntermediateSpecialties)
    )

    private val ssiCatalogCertifications: List<CatalogCertification> = listOf(
        CatalogCertification("Try Scuba Diving", specialties = listOf(Specialty("Try Scuba Diving"))),
        CatalogCertification("Scuba Diver", specialties = listOf(Specialty("Altitude Diving"))),
        CatalogCertification("Open Water Diver", specialties = listOf(Specialty("Ice Diving")))
    )

    private val tdiCatalogCertifications: List<CatalogCertification> = listOf(
        CatalogCertification("Open Circuit", specialties = listOf(Specialty("Nitrox"))),
        CatalogCertification("Rebreather", specialties = listOf(Specialty("Semi Closed rebreather")))
    )

    private lateinit var catalogViewModel: CatalogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        catalogViewModel = ViewModelProvider(this).get(CatalogViewModel::class.java)
        return FragmentCertificationCatalogBinding.inflate(inflater, container, false).apply {
            activity?.fab?.setOnClickListener {
                Toast.makeText(activity, "Search Catalog", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.certificationScanFragment)
            }
            activity?.fab?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_action_add))
            subscribeUi(this)
        }.root
    }

    private fun subscribeUi(binding: FragmentCertificationCatalogBinding) {
        configureCertificationRecyclerView(binding)
        configureAgencyFilter(binding)
    }

    private fun configureCertificationRecyclerView(binding: FragmentCertificationCatalogBinding) {
        binding.certPathLevelRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CertificationAdapter(padiCatalogCertifications)
            val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun configureAgencyFilter(binding: FragmentCertificationCatalogBinding) {
        binding.apply {
            agencyFilters.setOnCheckedChangeListener { chipGroup, checkedId ->
                val selectedChipText = chipGroup.findViewById<Chip>(checkedId)?.text
                Toast.makeText(
                    chipGroup.context,
                    selectedChipText ?: "No Choice",
                    Toast.LENGTH_LONG
                )
                    .show()
                certPathLevelRv.adapter = when (checkedId) {
                    R.id.chip_padi -> CertificationAdapter(padiCatalogCertifications)
                    R.id.chip_sdi -> CertificationAdapter(sdiCatalogCertifications)
                    R.id.chip_ssi -> CertificationAdapter(ssiCatalogCertifications)
                    R.id.chip_tdi -> CertificationAdapter(tdiCatalogCertifications)
                    else -> CertificationAdapter(padiCatalogCertifications)
                }
            }
        }
    }
}
