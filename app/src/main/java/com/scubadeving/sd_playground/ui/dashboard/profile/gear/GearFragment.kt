package com.scubadeving.sd_playground.ui.dashboard.profile.gear

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.activity_main.*

class GearFragment : Fragment() {

    private lateinit var gearViewModel: GearViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gearViewModel = ViewModelProvider(this).get(GearViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_gear, container, false)
        val textView: TextView = root.findViewById(R.id.text_gear)
        gearViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Gear", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(android.R.drawable.ic_input_add))
        return root
    }
}