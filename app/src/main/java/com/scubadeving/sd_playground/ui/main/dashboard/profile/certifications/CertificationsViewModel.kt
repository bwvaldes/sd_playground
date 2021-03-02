package com.scubadeving.sd_playground.ui.main.dashboard.profile.certifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.data.source.repository.DiverRepository

class CertificationsViewModel(private val diverRepository: DiverRepository) : ViewModel() {

    private val _currentDiver = MutableLiveData<Diver>().apply {
        diverRepository.getDiver("lEnWGcqDvI87XZvieJfY") { value = it }
    }

    val currentDiver: LiveData<Diver> = _currentDiver
}
