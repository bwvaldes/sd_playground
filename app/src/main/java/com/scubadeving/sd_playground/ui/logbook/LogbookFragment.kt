package com.scubadeving.sd_playground.ui.logbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.viewpager.LogbookViewPagerAdapter
import com.scubadeving.sd_playground.ui.adapters.viewpager.ProfileViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_logbook.*

class LogbookFragment : Fragment() {

    private lateinit var logbookViewPagerAdapter: LogbookViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var logbookViewModel: LogbookViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logbookViewModel =
            ViewModelProvider(this).get(LogbookViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_logbook, container, false)
        val textView: TextView = root.findViewById(R.id.text_logbook)
        logbookViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Log", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(android.R.drawable.ic_input_add))
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logbook_pager.isUserInputEnabled = false;
        scan_log.setOnClickListener {
            Toast.makeText(activity, "Scan Log", Toast.LENGTH_SHORT).show()
        }
        logbookViewPagerAdapter = LogbookViewPagerAdapter(this)
        viewPager = view.findViewById(R.id.logbook_pager)
        viewPager.adapter = logbookViewPagerAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.logbook_tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Dives"
                1 -> "Wildlife"
                2 -> "Map"
                else -> "Dives"
            }
        }.attach()
    }

}