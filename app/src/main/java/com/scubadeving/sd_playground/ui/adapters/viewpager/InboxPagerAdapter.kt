package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.details.codes.scan.QRCodeScanFragment
import com.scubadeving.sd_playground.ui.main.inbox.messages.MessagesFragment
import com.scubadeving.sd_playground.ui.main.inbox.notifications.NotificationsFragment

class InboxPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = INBOX_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            INBOX_MESSAGES -> MessagesFragment()
            INBOX_NOTIFICATIONS -> NotificationsFragment()
            else -> QRCodeScanFragment()
        }
    }

    companion object {
        private const val INBOX_MESSAGES = 0
        private const val INBOX_NOTIFICATIONS = 1
        private const val INBOX_PAGES = 2
    }
}
