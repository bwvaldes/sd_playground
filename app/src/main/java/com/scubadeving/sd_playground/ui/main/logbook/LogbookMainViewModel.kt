package com.scubadeving.sd_playground.ui.main.logbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogbookMainViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Logbook Main Fragment"
    }
    val text: LiveData<String> = _text
}
