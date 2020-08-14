package com.scubadeving.sd_playground.ui.main.dashboard.profile.about

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

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel = ViewModelProvider(this).get(AboutViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_profile_about, container, false)
        val textView: TextView = root.findViewById(R.id.text_about)
        aboutViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Edit About", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_edit))
        return root
    }
}