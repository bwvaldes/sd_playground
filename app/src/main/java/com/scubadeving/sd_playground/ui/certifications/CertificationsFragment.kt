package com.scubadeving.sd_playground.ui.certifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.R

class CertificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: CertificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(CertificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_certifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_certifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}