package com.scubadeving.sd_playground.ui.details.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scubadeving.sd_playground.databinding.FragmentWeatherDetailsBinding

class WeatherDetailFragment : Fragment() {

    private lateinit var weatherDetailViewModel: WeatherDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        weatherDetailViewModel = ViewModelProvider(this).get(WeatherDetailViewModel::class.java)

        return FragmentWeatherDetailsBinding.inflate(inflater, container, false).apply {
            weatherToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        }.root
    }
}
