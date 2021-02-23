package com.scubadeving.sd_playground.ui.main.dashboard.profile.gear

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GearViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Gear Fragment"
    }
    val text: LiveData<String> = _text
}
