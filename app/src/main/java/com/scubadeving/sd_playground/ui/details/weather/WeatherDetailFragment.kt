package com.scubadeving.sd_playground.ui.details.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.fragment_detail_weather.weather_toolbar

class WeatherDetailFragment : Fragment() {

    private lateinit var weatherDetailViewModel: WeatherDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        weatherDetailViewModel = ViewModelProvider(this).get(WeatherDetailViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_detail_weather, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Gear", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(android.R.drawable.ic_input_add))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weather_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }
}
