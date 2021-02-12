package com.scubadeving.sd_playground.ui.main.inbox.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessagesViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Messages Fragment"
    }
    val text: LiveData<String> = _text}