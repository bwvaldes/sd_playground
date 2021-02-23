package com.scubadeving.sd_playground.ui.main.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.google.android.material.chip.Chip
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Certification
import com.scubadeving.sd_playground.data.Specialty
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertificationAdapter
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.fragment_catalog.agency_filters
import kotlinx.android.synthetic.main.fragment_catalog.cert_path_level_rv

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
    private val padiCertifications: List<Certification> = listOf(
        Certification("Discover Snorkeling", listOf(Specialty("Discover Snorkeling"))),
        Certification("Seal Team", beginnerSpecialties),
        Certification("Bubble Maker", beginnerSpecialties),
        Certification("Discover Scuba Diving", beginnerSpecialties),
        Certification("Skin Diver", beginnerSpecialties),
        Certification("Open Water Diver", intermediateSpecialties),
        Certification("Scuba Diver", intermediateSpecialties),
        Certification("Adventure Diver", adventureSpecialties),
        Certification("Advanced Open Water Diver", advancedSpecialties.plus(emergencySpecialties)),
        Certification("Rescue Diver", rescueSpecialties),
        Certification("Master Scuba Diver", listOf(Specialty("Master Scuba Diver")))
    )

    private val sdiIntermediateSpecialties: List<Specialty> = listOf(
        Specialty("Advanced Adventure"),
        Specialty("Advanced Buoyancy"),
        Specialty("Altitude"),
        Specialty("Boat")
    )

    private val sdiCertifications: List<Certification> = listOf(
        Certification("Open Water Scuba Diver", sdiIntermediateSpecialties),
        Certification("Advanced Diver", sdiIntermediateSpecialties),
        Certification("Rescue Diver", sdiIntermediateSpecialties)
    )

    private val ssiCertifications: List<Certification> = listOf(
        Certification("Try Scuba Diving", listOf(Specialty("Try Scuba Diving"))),
        Certification("Scuba Diver", listOf(Specialty("Altitude Diving"))),
        Certification("Open Water Diver", listOf(Specialty("Ice Diving")))
    )

    private val tdiCertifications: List<Certification> = listOf(
        Certification("Open Circuit", listOf(Specialty("Nitrox"))),
        Certification("Rebreather", listOf(Specialty("Semi Closed rebreather")))
    )

    private lateinit var homeViewModel: CatalogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(CatalogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_catalog, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Search Catalog", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.certificationScanFragment)
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_add))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureCertificationRecyclerView()
        configureAgencyFilter()
    }

    private fun configureCertificationRecyclerView() {
        cert_path_level_rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CertificationAdapter(padiCertifications)
            val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun configureAgencyFilter() {
        agency_filters.setOnCheckedChangeListener { chipGroup, checkedId ->
            val selectedChipText = chipGroup.findViewById<Chip>(checkedId)?.text
            Toast.makeText(chipGroup.context, selectedChipText ?: "No Choice", Toast.LENGTH_LONG)
                .show()
            cert_path_level_rv.adapter = when (checkedId) {
                R.id.chip_padi -> CertificationAdapter(padiCertifications)
                R.id.chip_sdi -> CertificationAdapter(sdiCertifications)
                R.id.chip_ssi -> CertificationAdapter(ssiCertifications)
                R.id.chip_tdi -> CertificationAdapter(tdiCertifications)
                else -> CertificationAdapter(padiCertifications)
            }
        }
    }
}
