package com.scubadeving.sd_playground.ui.main.logbook.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogbookEntryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Logbook Entry Fragment"
    }
    val text: LiveData<String> = _text
}