package com.scubadeving.sd_playground.ui.main.dashboard.profile.certifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.data.source.repository.DiverRepository

@Suppress("UNCHECKED_CAST")
class CertificationsViewModelFactory(private val diverRepository: DiverRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CertificationsViewModel::class.java)) {
            return CertificationsViewModel(diverRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
