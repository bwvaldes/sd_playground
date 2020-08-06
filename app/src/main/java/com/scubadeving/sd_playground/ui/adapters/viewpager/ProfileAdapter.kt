package com.scubadeving.sd_playground.ui.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.profile.about.AboutFragment
import com.scubadeving.sd_playground.ui.profile.certifications.CertificationsFragment
import com.scubadeving.sd_playground.ui.profile.gear.GearFragment
import com.scubadeving.sd_playground.ui.profile.stats.StatsFragment

class ProfileAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CertificationsFragment()
            1 -> StatsFragment()
            2 -> GearFragment()
            3 -> AboutFragment()
            else -> CertificationsFragment()
        }
    }
}