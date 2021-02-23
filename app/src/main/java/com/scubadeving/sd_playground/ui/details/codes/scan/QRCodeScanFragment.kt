package com.scubadeving.sd_playground.ui.details.codes.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.fragment_detail_qrcode_scan.qrcode_add_from_gallery

class QRCodeScanFragment : Fragment() {

    private lateinit var qrCodeScanViewModel: QRCodeScanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        qrCodeScanViewModel = ViewModelProvider(this).get(QRCodeScanViewModel::class.java)
        return inflater.inflate(R.layout.fragment_detail_qrcode_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        qrcode_add_from_gallery.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "Just Clicked Add from Gallery!", Toast.LENGTH_SHORT).show()
        }
    }
}
