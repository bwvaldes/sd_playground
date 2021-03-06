package com.scubadeving.sd_playground.ui.main.dashboard.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.data.source.repository.DiverRepository

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val diverRepository: DiverRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(diverRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
