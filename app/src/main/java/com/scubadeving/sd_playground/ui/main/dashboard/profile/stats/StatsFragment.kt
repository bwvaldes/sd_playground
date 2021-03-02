package com.scubadeving.sd_playground.ui.main.dashboard.profile.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.scubadeving.sd_playground.data.source.repository.DiverRepository
import com.scubadeving.sd_playground.databinding.FragmentProfileStatsBinding

class StatsFragment : Fragment() {

    private val statsViewModel: StatsViewModel by viewModels {
        StatsViewModelFactory(DiverRepository(FirebaseFirestore.getInstance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentProfileStatsBinding.inflate(inflater, container, false).apply {
            statsViewModel.currentDiver.observe(viewLifecycleOwner, Observer { diver = it })
        }.root
    }
}
