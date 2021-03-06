package com.scubadeving.sd_playground.ui.main.dashboard.profile.gear

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.data.source.repository.DiverRepository

@Suppress("UNCHECKED_CAST")
class GearViewModelFactory(private val diverRepository: DiverRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GearViewModel::class.java)) {
            return GearViewModel(diverRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
