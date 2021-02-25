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
import com.scubadeving.sd_playground.ui.adapters.viewpager.ExploreViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.fragment_explore.explore_pager
import kotlinx.android.synthetic.main.fragment_explore.explore_search
import kotlinx.android.synthetic.main.fragment_explore.explore_tab_layout

class ExploreFragment : Fragment() {

    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreViewModel =
            ViewModelProvider(this).get(ExploreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Explore", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_action_search))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        explore_search.setOnClickListener {
            Toast.makeText(activity, "Search", Toast.LENGTH_SHORT).show()
        }
        explore_pager.apply {
            isUserInputEnabled = false
            adapter = ExploreViewPagerAdapter(requireParentFragment())
            TabLayoutMediator(explore_tab_layout, this) { tab, position ->
                tab.text = when (position) {
                    EXPLORE_TAB_SITES -> getString(R.string.explore_tab_sites)
                    EXPLORE_TAB_WILDLIFE -> getString(R.string.explore_tab_wildlife)
                    EXPLORE_TAB_BUDDIES -> getString(R.string.explore_tab_buddies)
                    else -> getString(R.string.explore_tab_sites)
                }
            }.attach()
        }
    }

    companion object {
        private const val EXPLORE_TAB_SITES = 0
        private const val EXPLORE_TAB_WILDLIFE = 1
        private const val EXPLORE_TAB_BUDDIES = 2
    }
}
