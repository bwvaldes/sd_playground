package com.scubadeving.sd_playground.ui.details.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.viewpager.WildlifeViewPagerAdapter

class WildlifeFragment : Fragment() {

    private lateinit var wildlifeViewPagerAdapter: WildlifeViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var wildlifeViewModel: WildlifeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeViewModel =
            ViewModelProvider(this).get(WildlifeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_wildlife, container, false)
        val textView: TextView = root.findViewById(R.id.text_wildlife)
        wildlifeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wildlifeViewPagerAdapter = WildlifeViewPagerAdapter(this)
        viewPager = view.findViewById(R.id.wildlife_pager)
        viewPager.adapter = wildlifeViewPagerAdapter
    }

}