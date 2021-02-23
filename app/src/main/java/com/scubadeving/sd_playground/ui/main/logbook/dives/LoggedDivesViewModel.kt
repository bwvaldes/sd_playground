package com.scubadeving.sd_playground.ui.main.logbook.dives

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoggedDivesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Logged Dives Fragment"
    }
    val text: LiveData<String> = _text
}
