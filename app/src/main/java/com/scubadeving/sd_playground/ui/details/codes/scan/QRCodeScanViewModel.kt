package com.scubadeving.sd_playground.ui.details.codes.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QRCodeScanViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is QR Code Scan Fragment"
    }
    val text: LiveData<String> = _text
}
