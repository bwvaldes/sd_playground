package com.scubadeving.sd_playground.ui.details.wildlife.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scubadeving.sd_playground.databinding.FragmentWildlifeOverviewBinding

class WildlifeOverviewFragment : Fragment() {

    private lateinit var wildlifeOverviewViewModel: WildlifeOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeOverviewViewModel = ViewModelProvider(this).get(WildlifeOverviewViewModel::class.java)
        return FragmentWildlifeOverviewBinding.inflate(inflater, container, false).apply {
            wildlifeOverviewToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        }.root
    }
}
