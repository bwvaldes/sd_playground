package com.scubadeving.sd_playground.ui.details.wildlife

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WildlifeMainViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Wildlife Fragment"
    }
    val text: LiveData<String> = _text
}