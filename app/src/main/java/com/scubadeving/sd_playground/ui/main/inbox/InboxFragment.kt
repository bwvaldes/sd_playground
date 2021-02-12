package com.scubadeving.sd_playground.ui.main.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.viewpager.InboxPagerAdapter
import kotlinx.android.synthetic.main.fragment_inbox.*

class InboxFragment : Fragment() {

    private lateinit var inboxViewModel: InboxViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inboxViewModel = ViewModelProvider(this).get(InboxViewModel::class.java)
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inbox_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        inbox_pager.apply {
            adapter = InboxPagerAdapter(requireParentFragment())
            TabLayoutMediator(inbox_tab_layout, this) { tab, position ->
                tab.text = when (position) {
                    INBOX_TAB_MESSAGES -> getString(R.string.inbox_tab_messages)
                    INBOX_TAB_NOTIFICATIONS -> getString(R.string.inbox_tab_notifications)
                    else -> getString(R.string.qrcode_tab_scan)
                }
            }.attach()
        }
    }

    companion object {
        private const val INBOX_TAB_MESSAGES = 0
        private const val INBOX_TAB_NOTIFICATIONS = 1
    }
}