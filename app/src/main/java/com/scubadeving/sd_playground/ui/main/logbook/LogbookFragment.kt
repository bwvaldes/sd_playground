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
import com.scubadeving.sd_playground.ui.adapters.viewpager.LogbookViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_logbook.*

class LogbookFragment : Fragment() {

    private lateinit var logbookViewModel: LogbookViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logbookViewModel =
            ViewModelProvider(this).get(LogbookViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_logbook, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Log", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(android.R.drawable.ic_input_add))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logbook_sort.setOnClickListener {
            Toast.makeText(activity, "Toggle Sort", Toast.LENGTH_SHORT).show()
        }
        logbook_pager.apply {
            isUserInputEnabled = false
            adapter = LogbookViewPagerAdapter(requireParentFragment())
            TabLayoutMediator(logbook_tab_layout, this) { tab, position ->
                tab.text = when (position) {
                    LOGBOOK_TAB_DIVES -> getString(R.string.logbook_tab_dives)
                    LOGBOOK_TAB_WILDLIFE -> getString(R.string.logbook_tab_wildlife)
                    LOGBOOK_TAB_MAP -> getString(R.string.logbook_tab_map)
                    else -> getString(R.string.logbook_tab_dives)
                }
            }.attach()
        }
    }

    companion object {
        private const val LOGBOOK_TAB_DIVES = 0
        private const val LOGBOOK_TAB_WILDLIFE = 1
        private const val LOGBOOK_TAB_MAP = 2
    }
}