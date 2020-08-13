package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.dashboard.profile.about.AboutFragment
import com.scubadeving.sd_playground.ui.dashboard.profile.certifications.CertificationsFragment
import com.scubadeving.sd_playground.ui.dashboard.profile.gear.GearFragment
import com.scubadeving.sd_playground.ui.dashboard.profile.stats.StatsFragment
import com.scubadeving.sd_playground.ui.explore.buddies.ExploreBuddiesFragment
import com.scubadeving.sd_playground.ui.explore.sites.ExploreSitesFragment
import com.scubadeving.sd_playground.ui.explore.wildlife.ExploreWildlifeFragment
import com.scubadeving.sd_playground.ui.logbook.dives.LoggedDivesFragment
import com.scubadeving.sd_playground.ui.logbook.map.LoggedDivesMapFragment
import com.scubadeving.sd_playground.ui.logbook.wildlife.LoggedWildlifeFragment

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