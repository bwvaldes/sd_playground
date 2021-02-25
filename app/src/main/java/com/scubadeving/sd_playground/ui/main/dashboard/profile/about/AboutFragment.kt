package com.scubadeving.sd_playground.ui.main.dashboard.profile.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.activity_main.fab

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel = ViewModelProvider(this).get(AboutViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_profile_about, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Edit About", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_action_edit))
        return root
    }
}
