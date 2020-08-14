package com.scubadeving.sd_playground.ui.main.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.viewpager.ExploreViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_explore.*

class ExploreFragment : Fragment() {

    private lateinit var exploreViewPagerAdapter: ExploreViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreViewModel =
            ViewModelProvider(this).get(ExploreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        val textView: TextView = root.findViewById(R.id.text_explore)
        exploreViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Explore", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        explore_pager.isUserInputEnabled = false
        exploreViewPagerAdapter = ExploreViewPagerAdapter(this)
        viewPager = view.findViewById(R.id.explore_pager)
        viewPager.adapter = exploreViewPagerAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.explore_tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Sites"
                1 -> "Wildlife"
                2 -> "Buddies"
                else -> "Sites"
            }
        }.attach()
    }
}