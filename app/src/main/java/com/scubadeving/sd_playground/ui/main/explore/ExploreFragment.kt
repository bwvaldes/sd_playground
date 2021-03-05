package com.scubadeving.sd_playground.ui.main.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.databinding.FragmentExploreBinding
import com.scubadeving.sd_playground.ui.adapters.viewpager.ExploreViewPagerAdapter

class ExploreFragment : Fragment() {

    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreViewModel = ViewModelProvider(this).get(ExploreViewModel::class.java)
        return FragmentExploreBinding.inflate(inflater, container, false).apply {
            exploreSearch.setOnClickListener {
                Toast.makeText(activity, "Search", Toast.LENGTH_SHORT).show()
            }
            explorePager.apply {
                isUserInputEnabled = false
                adapter = ExploreViewPagerAdapter(requireParentFragment())
                TabLayoutMediator(exploreTabLayout, this) { tab, position ->
                    tab.text = when (position) {
                        EXPLORE_TAB_SITES -> getString(R.string.explore_tab_sites)
                        EXPLORE_TAB_WILDLIFE -> getString(R.string.explore_tab_wildlife)
                        EXPLORE_TAB_BUDDIES -> getString(R.string.explore_tab_buddies)
                        else -> getString(R.string.explore_tab_sites)
                    }
                }.attach()
            }
        }.root
    }

    companion object {
        private const val EXPLORE_TAB_SITES = 0
        private const val EXPLORE_TAB_WILDLIFE = 1
        private const val EXPLORE_TAB_BUDDIES = 2
    }
}
