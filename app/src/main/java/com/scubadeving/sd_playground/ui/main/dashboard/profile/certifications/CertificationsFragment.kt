package com.scubadeving.sd_playground.ui.main.dashboard.profile.certifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.scubadeving.sd_playground.data.source.repository.DiverRepository
import com.scubadeving.sd_playground.databinding.FragmentProfileCertificationsBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.CertificationAdapter

class CertificationsFragment : Fragment() {

    private val certificationsViewModel: CertificationsViewModel by viewModels {
        CertificationsViewModelFactory(DiverRepository(FirebaseFirestore.getInstance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentProfileCertificationsBinding.inflate(inflater, container, false).apply {
            certificationsViewModel.currentDiver.observe(
                viewLifecycleOwner,
                Observer { diver ->
                    certCardRv.apply {
                        diver.certifications?.let {
                            adapter = CertificationAdapter(emptyList(), it, false)
                        }
//                        addItemDecoration(GridSpacingItemDecoration())
                    }
                }
            )
        }.root
    }
}
