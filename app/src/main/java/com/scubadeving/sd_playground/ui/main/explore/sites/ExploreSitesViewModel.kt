package com.scubadeving.sd_playground.ui.main.explore.sites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExploreSitesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Explore Sites Fragment"
    }
    val text: LiveData<String> = _text
}