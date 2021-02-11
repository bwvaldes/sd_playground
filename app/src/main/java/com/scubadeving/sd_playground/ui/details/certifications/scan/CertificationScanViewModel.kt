package com.scubadeving.sd_playground.ui.details.certifications.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CertificationScanViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Certification Scan Fragment"
    }
    val text: LiveData<String> = _text
}