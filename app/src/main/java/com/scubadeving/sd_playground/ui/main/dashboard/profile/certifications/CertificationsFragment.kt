package com.scubadeving.sd_playground.ui.main.dashboard.profile.certifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.VERTICAL
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Certification
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.GridSpacingItemDecoration
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertificationAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile_certifications.*

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
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val certCards: List<Certification> = listOf(
            Certification("Discover Diver"),
            Certification("Project AWARE Diver"),
            Certification("Open Water Diver"),
            Certification("Advanced Open Water Diver"),
            Certification("Equipment Specialist"),
            Certification("Night Dive"),
            Certification("Nitrox Diver"),
            Certification("Deep Diver"),
            Certification("Public Safety Diver"),
            Certification("Cavern Diver"),
            Certification("Against Debris"),
            Certification("Rebreather Diver"),
            Certification("Sidemount Diver"),
            Certification("Peak Performance Buoyancy"),
            Certification("Search and Recovery")
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