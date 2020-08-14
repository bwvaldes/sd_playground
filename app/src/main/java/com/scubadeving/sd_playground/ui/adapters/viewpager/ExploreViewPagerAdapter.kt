package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.main.explore.buddies.ExploreBuddiesFragment
import com.scubadeving.sd_playground.ui.main.explore.sites.ExploreSitesFragment
import com.scubadeving.sd_playground.ui.main.explore.wildlife.ExploreWildlifeFragment

class ExploreViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ExploreSitesFragment()
            1 -> ExploreWildlifeFragment()
            2 -> ExploreBuddiesFragment()
            else -> ExploreSitesFragment()
        }
    }
}