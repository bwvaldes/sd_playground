package com.scubadeving.sd_playground.ui.details.certifications.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scubadeving.sd_playground.databinding.FragmentCertificationDetailScanBinding

class CertificationScanFragment : Fragment() {

    private lateinit var certificationScanViewModel: CertificationScanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        certificationScanViewModel = ViewModelProvider(this).get(CertificationScanViewModel::class.java)
        return FragmentCertificationDetailScanBinding.inflate(inflater, container, false).apply {
            certScanToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            certScanCert.setOnClickListener {
                Toast.makeText(activity, "Scan Cert", Toast.LENGTH_SHORT).show()
            }
            certScanAddManually.setOnClickListener {
                Toast.makeText(activity, "Just Clicked Add Cert Manually", Toast.LENGTH_SHORT).show()
            }
        }.root
    }
}
