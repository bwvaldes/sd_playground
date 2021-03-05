package com.scubadeving.sd_playground.ui.details.codes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.databinding.FragmentQrcodeMainBinding
import com.scubadeving.sd_playground.ui.adapters.viewpager.QRCodePagerAdapter

class QRCodeMainFragment : Fragment() {

    private lateinit var qrCodeViewModel: QRCodeMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        qrCodeViewModel = ViewModelProvider(this).get(QRCodeMainViewModel::class.java)
        return FragmentQrcodeMainBinding.inflate(inflater, container, false).apply {
            qrcodeToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            qrcodePager.apply {
                adapter = QRCodePagerAdapter(requireParentFragment())
                TabLayoutMediator(qrcodeTabLayout, this) { tab, position ->
                    tab.text = when (position) {
                        QR_CODE_TAB_SCAN -> getString(R.string.qrcode_tab_scan)
                        QR_CODE_TAB_CODE -> getString(R.string.qrcode_tab_code)
                        else -> getString(R.string.qrcode_tab_scan)
                    }
                }.attach()
            }
        }.root
    }

    companion object {
        private const val QR_CODE_TAB_SCAN = 0
        private const val QR_CODE_TAB_CODE = 1
    }
}
