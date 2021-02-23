package com.scubadeving.sd_playground.ui.details.certifications.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.fragment_detail_cert_scan.cert_scan_add_manually
import kotlinx.android.synthetic.main.fragment_detail_cert_scan.cert_scan_cert
import kotlinx.android.synthetic.main.fragment_detail_cert_scan.cert_scan_toolbar

class CertificationScanFragment : Fragment() {

    private lateinit var certificationScanViewModel: CertificationScanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        certificationScanViewModel = ViewModelProvider(this).get(CertificationScanViewModel::class.java)
        return inflater.inflate(R.layout.fragment_detail_cert_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cert_scan_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        cert_scan_cert.setOnClickListener {
            Toast.makeText(activity, "Scan Cert", Toast.LENGTH_SHORT).show()
        }
        cert_scan_add_manually.setOnClickListener {
            Toast.makeText(activity, "Just Clicked Add Cert Manually", Toast.LENGTH_SHORT).show()
        }
    }
}
