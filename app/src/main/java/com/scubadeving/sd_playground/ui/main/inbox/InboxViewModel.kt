package com.scubadeving.sd_playground.ui.main.inbox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InboxViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Inbox Fragment"
    }
    val text: LiveData<String> = _text
}
