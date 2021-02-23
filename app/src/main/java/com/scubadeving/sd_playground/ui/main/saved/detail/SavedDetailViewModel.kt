package com.scubadeving.sd_playground.ui.main.saved.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedDetailViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Saved Detail Fragment"
    }
    val text: LiveData<String> = _text
}
