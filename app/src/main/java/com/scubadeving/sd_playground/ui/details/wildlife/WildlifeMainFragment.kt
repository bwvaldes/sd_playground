package com.scubadeving.sd_playground.ui.details.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.viewpager.WildlifeViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_detail_wildlife_main.*

class WildlifeMainFragment : Fragment() {

    private lateinit var wildlifeMainViewModel: WildlifeMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeMainViewModel =
            ViewModelProvider(this).get(WildlifeMainViewModel::class.java)
        return inflater.inflate(R.layout.fragment_detail_wildlife_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wildlife_pager.adapter = WildlifeViewPagerAdapter(this)
    }
}