package com.scubadeving.sd_playground.ui.main.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Certification
import com.scubadeving.sd_playground.data.Specialty
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertificationLevelAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_catalog.*


class CatalogFragment : Fragment() {

    private var beginnerSpecialties = listOf(
        Specialty("AWARE - Coral Reef Conservation"),
        Specialty("Project AWARE Specialist"),
        Specialty("Digital Underwater Photographer (for Snorkelers)")
    )
    private var intermediateSpecialties = listOf(
        Specialty("Avanced Rebreather Diver"),
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
    private var adventureSpecialties = listOf(
        Specialty("Deep Diver"),
        Specialty("Wreck Diver")
    )
    private var advancedSpecialties = listOf(
        Specialty("Cavern Diver"),
        Specialty("Ice Diver"),
        Specialty("Search and Recovery Diver"),
        Specialty("Semiclosed Rebreather - Dolphin/Atlantis")
    )
    private var emergencySpecialties = listOf(
        Specialty("Emergency First Response Instructor"),
        Specialty("Emergency First Response Instructor Trainer")
    )
    private var padiCertifications: List<Certification> = listOf(
        Certification("Seal Team", beginnerSpecialties),
        Certification("Bubble Maker", beginnerSpecialties),
        Certification("Discover Scuba Diving", beginnerSpecialties),
        Certification("Skin Diver", beginnerSpecialties),
        Certification("Discover Snorkeling", emptyList()),
        Certification("Open Water Diver", intermediateSpecialties),
        Certification("Scuba Diver", intermediateSpecialties),
        Certification("Adventure Diver", adventureSpecialties),
        Certification("Advanced Open Water Diver", advancedSpecialties),
        Certification("Scuba Review", emptyList()),
        Certification("Emergency First Response Provider", emergencySpecialties),
        Certification("Rescue Diver", emptyList()),
        Certification("Master Scuba Diver", emptyList())
    )
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: CertificationLevelAdapter
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
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        cert_path_level_rv.layoutManager = layoutManager
        adapter = CertificationLevelAdapter(padiCertifications)
        cert_path_level_rv.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(cert_path_level_rv.context, layoutManager.orientation)
        cert_path_level_rv.addItemDecoration(dividerItemDecoration)
    }
}