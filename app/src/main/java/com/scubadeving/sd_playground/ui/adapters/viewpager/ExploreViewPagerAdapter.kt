package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.main.explore.buddies.ExploreBuddiesFragment
import com.scubadeving.sd_playground.ui.main.explore.sites.ExploreSitesFragment
import com.scubadeving.sd_playground.ui.main.explore.wildlife.ExploreWildlifeFragment

class ExploreViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = EXPLORE_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            EXPLORE_SITES -> ExploreSitesFragment()
            EXPLORE_WILDLIFE -> ExploreWildlifeFragment()
            EXPLORE_BUDDIES -> ExploreBuddiesFragment()
            else -> ExploreSitesFragment()
        }
    }

    companion object {
        private const val EXPLORE_SITES = 0
        private const val EXPLORE_WILDLIFE = 1
        private const val EXPLORE_BUDDIES = 2
        private const val EXPLORE_PAGES = 3
    }
}
