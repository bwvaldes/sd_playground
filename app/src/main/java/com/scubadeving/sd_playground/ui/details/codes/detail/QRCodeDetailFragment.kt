package com.scubadeving.sd_playground.ui.details.codes.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.databinding.FragmentQrcodeDetailsBinding

class QRCodeDetailFragment : Fragment() {

    private lateinit var qrCodeDetailViewModel: QRCodeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        qrCodeDetailViewModel = ViewModelProvider(this).get(QRCodeDetailViewModel::class.java)
        return FragmentQrcodeDetailsBinding.inflate(inflater, container, false).apply {
            qrcodeShare.setOnClickListener {
                Toast.makeText(context, "Just Clicked QR Code Share!", Toast.LENGTH_SHORT).show()
            }
            qrcodeSave.setOnClickListener {
                Toast.makeText(context, "Just Clicked QR Code Save!", Toast.LENGTH_SHORT).show()
            }
        }.root
    }
}
