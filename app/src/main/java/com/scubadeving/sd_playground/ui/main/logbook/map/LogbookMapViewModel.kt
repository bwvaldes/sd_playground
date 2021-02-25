package com.scubadeving.sd_playground.ui.main.logbook.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogbookMapViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Logbook Map Fragment"
    }
    val text: LiveData<String> = _text
}
