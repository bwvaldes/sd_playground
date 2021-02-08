package com.scubadeving.sd_playground.ui.details.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.activity_main.*

class WeatherDetailFragment : Fragment() {

    private lateinit var weatherDetailViewModel: WeatherDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        weatherDetailViewModel = ViewModelProvider(this).get(WeatherDetailViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_detail_weather, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Gear", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(android.R.drawable.ic_input_add))
        val toolbar: androidx.appcompat.widget.Toolbar = root.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        return root
    }
}