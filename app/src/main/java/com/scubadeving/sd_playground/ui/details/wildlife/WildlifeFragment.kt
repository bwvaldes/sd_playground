package com.scubadeving.sd_playground.ui.details.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.viewpager.WildlifeViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_wildlife.*

class WildlifeFragment : Fragment() {

    private lateinit var wildlifeViewModel: WildlifeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeViewModel =
            ViewModelProvider(this).get(WildlifeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_wildlife, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wildlife_pager.adapter = WildlifeViewPagerAdapter(this)
    }
}