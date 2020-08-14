package com.scubadeving.sd_playground.ui.main.logbook.wildlife

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoggedWildlifeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Logged Wildlife Fragment"
    }
    val text: LiveData<String> = _text
}