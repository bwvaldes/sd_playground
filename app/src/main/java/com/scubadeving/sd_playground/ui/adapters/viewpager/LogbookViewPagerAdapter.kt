package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.main.logbook.dives.LoggedDivesFragment
import com.scubadeving.sd_playground.ui.main.logbook.map.LoggedDivesMapFragment
import com.scubadeving.sd_playground.ui.main.logbook.wildlife.LoggedWildlifeFragment

class LogbookViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = LOGBOOK_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            LOGBOOK_DIVES -> LoggedDivesFragment()
            LOGBOOK_WILDLIFE -> LoggedWildlifeFragment()
            LOGBOOK_DIVES_MAP -> LoggedDivesMapFragment()
            else -> LoggedDivesFragment()
        }
    }

    companion object {
        private const val LOGBOOK_DIVES = 0
        private const val LOGBOOK_WILDLIFE = 1
        private const val LOGBOOK_DIVES_MAP = 2
        private const val LOGBOOK_PAGES = 3
    }
}
