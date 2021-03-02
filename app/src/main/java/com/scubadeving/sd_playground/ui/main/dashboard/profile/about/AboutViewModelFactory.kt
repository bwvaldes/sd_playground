package com.scubadeving.sd_playground.ui.main.dashboard.profile.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.data.source.repository.DiverRepository

class AboutViewModelFactory(private val diverRepository: DiverRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AboutViewModel::class.java)) {
            return AboutViewModel(diverRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
