package com.scubadeving.sd_playground.ui.details.codes.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QRCodeDetailViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is QR Code Detail Fragment"
    }
    val text: LiveData<String> = _text
}
