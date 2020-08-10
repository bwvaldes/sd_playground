package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.details.wildlife.WildlifeDetailFragment
import com.scubadeving.sd_playground.ui.details.wildlife.WildlifeOverviewFragment

class WildlifeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WildlifeOverviewFragment()
            1 -> WildlifeDetailFragment()
            else -> WildlifeOverviewFragment()
        }
    }
}