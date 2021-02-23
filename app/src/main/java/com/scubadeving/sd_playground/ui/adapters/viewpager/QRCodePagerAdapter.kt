package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.details.codes.detail.QRCodeDetailFragment
import com.scubadeving.sd_playground.ui.details.codes.scan.QRCodeScanFragment

class QRCodePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = QR_CODE_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            QR_CODE_SCAN -> QRCodeScanFragment()
            QR_CODE_DETAIL -> QRCodeDetailFragment()
            else -> QRCodeScanFragment()
        }
    }

    companion object {
        private const val QR_CODE_SCAN = 0
        private const val QR_CODE_DETAIL = 1
        private const val QR_CODE_PAGES = 2
    }
}
