package com.scubadeving.sd_playground.ui.main.dashboard.profile.gear.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GearProfileDetailViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is About Fragment"
    }
    val text: LiveData<String> = _text
}
