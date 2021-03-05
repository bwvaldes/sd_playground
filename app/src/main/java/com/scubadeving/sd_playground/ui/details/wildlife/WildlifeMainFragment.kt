package com.scubadeving.sd_playground.ui.details.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.databinding.FragmentWildlifeMainBinding
import com.scubadeving.sd_playground.ui.adapters.viewpager.WildlifeViewPagerAdapter

class WildlifeMainFragment : Fragment() {

    private lateinit var wildlifeMainViewModel: WildlifeMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeMainViewModel =
            ViewModelProvider(this).get(WildlifeMainViewModel::class.java)
        return FragmentWildlifeMainBinding.inflate(inflater, container, false).apply {
            wildlifePager.adapter = WildlifeViewPagerAdapter(requireParentFragment())
        }.root
    }
}
