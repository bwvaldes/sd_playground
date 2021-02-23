package com.scubadeving.sd_playground.ui.main.dashboard.profile.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Stats Fragment"
    }
    val text: LiveData<String> = _text
}
