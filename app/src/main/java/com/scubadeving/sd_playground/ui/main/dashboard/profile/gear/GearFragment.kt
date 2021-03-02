package com.scubadeving.sd_playground.ui.main.dashboard.profile.gear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.GridLayoutManager.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.data.source.repository.DiverRepository
import com.scubadeving.sd_playground.databinding.FragmentProfileGearBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.GearAdapter

class GearFragment : Fragment() {

    private val gearViewModel: GearViewModel by viewModels {
        GearViewModelFactory(DiverRepository(FirebaseFirestore.getInstance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentProfileGearBinding.inflate(inflater, container, false).apply {
            gearViewModel.currentDiver.observe(
                viewLifecycleOwner,
                Observer {
                    diver = it
                    configureGearProfileList(it)
                    configureGearItems(it)
                }
            )
        }.root
    }

    private fun FragmentProfileGearBinding.configureGearProfileList(diver: Diver) {
        gearProfilesRv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = diver.gearProfiles?.let { GearAdapter(it, true) }
        }
        gearProfileGearList.text = diver.gear.toString()
    }

    private fun FragmentProfileGearBinding.configureGearItems(diver: Diver) {
        gearFilteredRv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = diver.gearProfiles?.let { GearAdapter(it, false) }
            setOnClickListener {
                Toast.makeText(context, "Just Clicked Gear Item!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
