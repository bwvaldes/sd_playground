package com.scubadeving.sd_playground.ui.main.explore.buddies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExploreBuddiesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Explore Buddies Fragment"
    }
    val text: LiveData<String> = _text
}
