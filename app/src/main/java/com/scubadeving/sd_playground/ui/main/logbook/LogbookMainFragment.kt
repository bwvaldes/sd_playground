package com.scubadeving.sd_playground.ui.main.logbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.viewpager.LogbookViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.fragment_logbook.logbook_import
import kotlinx.android.synthetic.main.fragment_logbook.logbook_pager
import kotlinx.android.synthetic.main.fragment_logbook.logbook_tab_layout

class LogbookMainFragment : Fragment() {

    private lateinit var logbookMainViewModel: LogbookMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logbookMainViewModel = ViewModelProvider(this).get(LogbookMainViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_logbook, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Log", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(ContextCompat.getDrawable(requireContext(), android.R.drawable.ic_input_add))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
            logbook_import.setOnClickListener {
                Toast.makeText(activity, "Add Log from computer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val LOGBOOK_TAB_DIVES = 0
        private const val LOGBOOK_TAB_WILDLIFE = 1
        private const val LOGBOOK_TAB_MAP = 2
    }
}
