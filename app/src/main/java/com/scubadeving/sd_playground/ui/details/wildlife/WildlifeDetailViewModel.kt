package com.scubadeving.sd_playground.ui.details.wildlife

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WildlifeDetailViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Wildlife Detail Fragment"
    }
    val text: LiveData<String> = _text
}