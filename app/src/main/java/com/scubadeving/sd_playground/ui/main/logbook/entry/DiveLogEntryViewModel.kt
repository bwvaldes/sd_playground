package com.scubadeving.sd_playground.ui.main.logbook.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiveLogEntryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Dive Log Entry Fragment"
    }
    val text: LiveData<String> = _text
}
