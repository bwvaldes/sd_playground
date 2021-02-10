package com.scubadeving.sd_playground.ui.main.explore.sites.filtered

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExploreSitesFilteredViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Explore Sites Filtered Fragment"
    }
    val text: LiveData<String> = _text
}