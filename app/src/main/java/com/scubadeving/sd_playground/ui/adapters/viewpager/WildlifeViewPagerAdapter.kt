package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.details.wildlife.detail.WildlifeDetailFragment
import com.scubadeving.sd_playground.ui.details.wildlife.overview.WildlifeOverviewFragment

class WildlifeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = WILDLIFE_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            WILDLIFE_OVERVIEW -> WildlifeOverviewFragment()
            WILDLIFE_DETAIL -> WildlifeDetailFragment()
            else -> WildlifeOverviewFragment()
        }
    }

    companion object {
        private const val WILDLIFE_OVERVIEW = 0
        private const val WILDLIFE_DETAIL = 1
        private const val WILDLIFE_PAGES = 2
    }
}