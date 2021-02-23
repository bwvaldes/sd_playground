package com.scubadeving.sd_playground.ui.details.wildlife.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.fragment_detail_wildlife_overview.wildlife_overview_toolbar

class WildlifeOverviewFragment : Fragment() {

    private lateinit var wildlifeOverviewViewModel: WildlifeOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeOverviewViewModel =
            ViewModelProvider(this).get(WildlifeOverviewViewModel::class.java)
        return inflater.inflate(R.layout.fragment_detail_wildlife_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wildlife_overview_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }
}
