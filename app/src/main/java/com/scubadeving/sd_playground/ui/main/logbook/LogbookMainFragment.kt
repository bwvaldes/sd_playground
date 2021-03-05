package com.scubadeving.sd_playground.ui.main.logbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.databinding.FragmentLogbookBinding
import com.scubadeving.sd_playground.ui.adapters.viewpager.LogbookViewPagerAdapter

class LogbookMainFragment : Fragment() {

    private lateinit var logbookMainViewModel: LogbookMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logbookMainViewModel = ViewModelProvider(this).get(LogbookMainViewModel::class.java)
        return FragmentLogbookBinding.inflate(inflater, container, false).apply {
            logbookPager.apply {
                isUserInputEnabled = false
                adapter = LogbookViewPagerAdapter(requireParentFragment())
                TabLayoutMediator(logbookTabLayout, this) { tab, position ->
                    tab.text = when (position) {
                        LOGBOOK_TAB_DIVES -> getString(R.string.logbook_tab_dives)
                        LOGBOOK_TAB_WILDLIFE -> getString(R.string.logbook_tab_wildlife)
                        LOGBOOK_TAB_MAP -> getString(R.string.logbook_tab_map)
                        else -> getString(R.string.logbook_tab_dives)
                    }
                }.attach()
                logbookImport.setOnClickListener {
                    Toast.makeText(activity, "Add Log from computer", Toast.LENGTH_SHORT).show()
                }
            }
        }.root
    }

    companion object {
        private const val LOGBOOK_TAB_DIVES = 0
        private const val LOGBOOK_TAB_WILDLIFE = 1
        private const val LOGBOOK_TAB_MAP = 2
    }
}
