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
import com.scubadeving.sd_playground.decorations.GridSpacingItemDecoration
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertCardAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile_certifications.*

class CertificationsFragment : Fragment() {

    var certCards: List<String> = listOf(
        "Discover Diver",
        "Project AWARE Diver",
        "Open Water Diver",
        "Advanced Open Water Diver",
        "Equipment Specialist",
        "Night Dive",
        "Nitrox Diver",
        "Deep Diver",
        "Public Safety Diver",
        "Cavern Diver",
        "Against Debris",
        "Rebreather Diver",
        "Sidemount Diver",
        "Peak Performance Buoyancy",
        "Search and Recovery"
    )
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
        val spanCount = 2
        val spacing = 15
        val includeEdge = true
        cert_card_rv.apply {
            layoutManager = GridLayoutManager(context, spanCount, VERTICAL, false)
            adapter = CertCardAdapter(certCards)
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        }
    }
}