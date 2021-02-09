package com.scubadeving.sd_playground.ui.details.codes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.viewpager.QRCodePagerAdapter
import kotlinx.android.synthetic.main.fragment_detail_qrcode_main.*

class QRCodeMainFragment : Fragment() {

    private lateinit var qrCodeViewModel: QRCodeMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        qrCodeViewModel = ViewModelProvider(this).get(QRCodeMainViewModel::class.java)
        return inflater.inflate(R.layout.fragment_detail_qrcode_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        qrcode_pager.apply {
            adapter = QRCodePagerAdapter(requireParentFragment())
            TabLayoutMediator(qrcode_tab_layout, this) { tab, position ->
                tab.text = when (position) {
                    QR_CODE_TAB_SCAN -> getString(R.string.qrcode_tab_scan)
                    QR_CODE_TAB_CODE -> getString(R.string.qrcode_tab_code)
                    else -> getString(R.string.qrcode_tab_scan)
                }
            }.attach()
        }
    }

    companion object {
        private const val QR_CODE_TAB_SCAN = 0
        private const val QR_CODE_TAB_CODE = 1
    }
}