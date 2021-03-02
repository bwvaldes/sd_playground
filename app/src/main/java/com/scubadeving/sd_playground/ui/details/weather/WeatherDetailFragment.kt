package com.scubadeving.sd_playground.ui.details.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.fragment_weather_details.weather_toolbar

class WeatherDetailFragment : Fragment() {

    private lateinit var weatherDetailViewModel: WeatherDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        weatherDetailViewModel = ViewModelProvider(this).get(WeatherDetailViewModel::class.java)

        return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weather_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }
}
