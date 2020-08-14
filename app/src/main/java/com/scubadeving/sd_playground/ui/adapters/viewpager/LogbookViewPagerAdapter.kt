package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.main.logbook.dives.LoggedDivesFragment
import com.scubadeving.sd_playground.ui.main.logbook.map.LoggedDivesMapFragment
import com.scubadeving.sd_playground.ui.main.logbook.wildlife.LoggedWildlifeFragment

class LogbookViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LoggedDivesFragment()
            1 -> LoggedWildlifeFragment()
            2 -> LoggedDivesMapFragment()
            else -> LoggedDivesFragment()
        }
    }
}