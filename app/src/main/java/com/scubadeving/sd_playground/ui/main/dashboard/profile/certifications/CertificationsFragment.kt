package com.scubadeving.sd_playground.ui.main.dashboard.profile.certifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.VERTICAL
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.certification.CatalogCertification
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertificationAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.fragment_profile_certifications.cert_card_rv

class CertificationsFragment : Fragment() {

    private lateinit var certificationsViewModel: CertificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        certificationsViewModel =
            ViewModelProvider(this).get(CertificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile_certifications, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Search My Certs", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_action_search))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val certCards: List<CatalogCertification> = listOf(
            CatalogCertification("Discover Diver"),
            CatalogCertification("Project AWARE Diver"),
            CatalogCertification("Open Water Diver"),
            CatalogCertification("Advanced Open Water Diver"),
            CatalogCertification("Equipment Specialist"),
            CatalogCertification("Night Dive"),
            CatalogCertification("Nitrox Diver"),
            CatalogCertification("Deep Diver"),
            CatalogCertification("Public Safety Diver"),
            CatalogCertification("Cavern Diver"),
            CatalogCertification("Against Debris"),
            CatalogCertification("Rebreather Diver"),
            CatalogCertification("Sidemount Diver"),
            CatalogCertification("Peak Performance Buoyancy"),
            CatalogCertification("Search and Recovery")
        )
        val spanCount = 2
        val spacing = 15
        val includeEdge = true
        cert_card_rv.apply {
            layoutManager = GridLayoutManager(context, spanCount, VERTICAL, false)
            adapter = CertificationAdapter(certCards, false)
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        }
    }
}
