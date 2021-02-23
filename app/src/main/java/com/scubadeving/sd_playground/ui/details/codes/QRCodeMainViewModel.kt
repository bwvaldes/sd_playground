package com.scubadeving.sd_playground.ui.details.codes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QRCodeMainViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Wildlife Fragment"
    }
    val text: LiveData<String> = _text
}
