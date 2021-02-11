package com.scubadeving.sd_playground.ui.details.certifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CertificationDetailViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Certification Detail Fragment"
    }
    val text: LiveData<String> = _text
}