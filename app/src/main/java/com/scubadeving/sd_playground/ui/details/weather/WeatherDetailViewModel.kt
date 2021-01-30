package com.scubadeving.sd_playground.ui.details.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherDetailViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Weather Detail Fragment"
    }
    val text: LiveData<String> = _text}