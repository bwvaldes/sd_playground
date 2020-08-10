package com.scubadeving.sd_playground.ui.details.divesite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiveSiteDetailViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Dive Site Detail Fragment"
    }
    val text: LiveData<String> = _text
}