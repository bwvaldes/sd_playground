package com.scubadeving.sd_playground.ui.main.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Explore Sites Filtered Fragment"
    }
    val text: LiveData<String> = _text
}