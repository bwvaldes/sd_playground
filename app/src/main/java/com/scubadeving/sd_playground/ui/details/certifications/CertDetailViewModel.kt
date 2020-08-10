package com.scubadeving.sd_playground.ui.details.certifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CertDetailViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Cert Detail Fragment"
    }
    val text: LiveData<String> = _text
}