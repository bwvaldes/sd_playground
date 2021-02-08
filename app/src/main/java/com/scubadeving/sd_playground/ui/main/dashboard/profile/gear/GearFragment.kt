package com.scubadeving.sd_playground.ui.main.dashboard.profile.gear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.activity_main.*

class GearFragment : Fragment() {

    private lateinit var gearViewModel: GearViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gearViewModel = ViewModelProvider(this).get(GearViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_profile_gear, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Gear", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(android.R.drawable.ic_input_add))
        return root
    }
}