package com.scubadeving.sd_playground.ui.main.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.data.source.repository.DiverRepository

@Suppress("UNCHECKED_CAST")
class DashboardViewModelFactory(private val diverRepository: DiverRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(diverRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
