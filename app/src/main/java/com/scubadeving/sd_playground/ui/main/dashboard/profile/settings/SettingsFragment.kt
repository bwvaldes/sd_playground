package com.scubadeving.sd_playground.ui.main.dashboard.profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        return FragmentSettingsBinding.inflate(inflater, container, false).apply {
            settingsToolbar.apply {
                setNavigationOnClickListener { findNavController().navigateUp() }
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.action_logout -> {
                            Toast.makeText(context, "Just Clicked logout!", Toast.LENGTH_SHORT).show()
                            true
                        }
                        else -> true
                    }
                }
            }
        }.root
    }
}
