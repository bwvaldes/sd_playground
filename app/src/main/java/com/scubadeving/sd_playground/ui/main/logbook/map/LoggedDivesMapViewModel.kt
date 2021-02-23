package com.scubadeving.sd_playground.ui.main.logbook.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoggedDivesMapViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Logged Dives Map Fragment"
    }
    val text: LiveData<String> = _text
}
