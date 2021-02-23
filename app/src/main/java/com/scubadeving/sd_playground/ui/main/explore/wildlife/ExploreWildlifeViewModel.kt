package com.scubadeving.sd_playground.ui.main.explore.wildlife

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExploreWildlifeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Explore Wildlife Fragment"
    }
    val text: LiveData<String> = _text
}
