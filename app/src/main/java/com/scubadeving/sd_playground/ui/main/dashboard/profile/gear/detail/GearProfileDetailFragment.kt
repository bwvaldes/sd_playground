package com.scubadeving.sd_playground.ui.main.dashboard.profile.gear.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.scubadeving.sd_playground.databinding.FragmentProfileGearDetailsBinding

class GearProfileDetailFragment : Fragment() {

    private lateinit var gearProfileDetailViewModel: GearProfileDetailViewModel
    private val args: GearProfileDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gearProfileDetailViewModel = ViewModelProvider(this).get(GearProfileDetailViewModel::class.java)
        return FragmentProfileGearDetailsBinding.inflate(inflater, container, false).apply {
            gearDetailToolbar.apply {
                setNavigationOnClickListener { findNavController().navigateUp() }
                title = args.gearProfileName
            }
        }.root
    }
}
