package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.main.dashboard.profile.about.AboutFragment
import com.scubadeving.sd_playground.ui.main.dashboard.profile.certifications.CertificationsFragment
import com.scubadeving.sd_playground.ui.main.dashboard.profile.gear.GearFragment
import com.scubadeving.sd_playground.ui.main.dashboard.profile.stats.StatsFragment

class ProfileViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = PROFILE_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            PROFILE_CERTIFICATIONS -> CertificationsFragment()
            PROFILE_STATS -> StatsFragment()
            PROFILE_GEAR -> GearFragment()
            PROFILE_ABOUT -> AboutFragment()
            else -> CertificationsFragment()
        }
    }

    companion object {
        private const val PROFILE_CERTIFICATIONS = 0
        private const val PROFILE_STATS = 1
        private const val PROFILE_GEAR = 2
        private const val PROFILE_ABOUT = 3
        private const val PROFILE_PAGES = 4
    }
}
