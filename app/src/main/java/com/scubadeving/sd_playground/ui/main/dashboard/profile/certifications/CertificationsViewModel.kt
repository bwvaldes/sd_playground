package com.scubadeving.sd_playground.ui.main.dashboard.profile.certifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CertificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is My Certs Fragment"
    }
    val text: LiveData<String> = _text
}
