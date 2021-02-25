package com.scubadeving.sd_playground.ui.details.codes.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.fragment_qrcode_details.qrcode_save
import kotlinx.android.synthetic.main.fragment_qrcode_details.qrcode_share

class QRCodeDetailFragment : Fragment() {

    private lateinit var qrCodeDetailViewModel: QRCodeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        qrCodeDetailViewModel = ViewModelProvider(this).get(QRCodeDetailViewModel::class.java)
        return inflater.inflate(R.layout.fragment_qrcode_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        qrcode_share.setOnClickListener {
            Toast.makeText(context, "Just Clicked QR Code Share!", Toast.LENGTH_SHORT).show()
        }
        qrcode_save.setOnClickListener {
            Toast.makeText(context, "Just Clicked QR Code Save!", Toast.LENGTH_SHORT).show()
        }
    }
}
