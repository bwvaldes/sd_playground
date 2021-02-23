package com.scubadeving.sd_playground.ui.main.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExploreViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Explore Fragment"
    }
    val text: LiveData<String> = _text
}
