package com.scubadeving.sd_playground.ui.main.dashboard.profile.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.scubadeving.sd_playground.data.source.repository.DiverRepository
import com.scubadeving.sd_playground.databinding.FragmentProfileAboutBinding

class AboutFragment : Fragment() {

    private val aboutViewModel: AboutViewModel by viewModels {
        AboutViewModelFactory(DiverRepository(FirebaseFirestore.getInstance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentProfileAboutBinding.inflate(inflater, container, false).apply {
            aboutViewModel.currentDiver.observe(
                viewLifecycleOwner,
                Observer {
                    diver = it
                    profileAboutInterests.text = diver?.about?.interests.toString()
                    profileAboutCauses.text = diver?.about?.supportedCauses.toString()
                }
            )
        }.root
    }
}
