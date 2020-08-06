package com.scubadeving.sd_playground.ui.adapters.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scubadeving.sd_playground.ui.certifications.CertificationsFragment
import com.scubadeving.sd_playground.utils.ARG_OBJECT

class ProfileAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = if (position == 0) {
            CertificationsFragment()
        } else ProfileObjectFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }

        return fragment
    }
}