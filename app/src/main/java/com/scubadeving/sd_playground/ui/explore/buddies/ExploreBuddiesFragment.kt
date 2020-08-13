package com.scubadeving.sd_playground.ui.explore.buddies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.recyclerview.LoggedDivesAdapter
import com.scubadeving.sd_playground.ui.explore.sites.ExploreSitesViewModel
import kotlinx.android.synthetic.main.fragment_logged_dives.*
import kotlinx.android.synthetic.main.fragment_logged_dives_map.*

class ExploreBuddiesFragment : Fragment() {

    private lateinit var exploreBuddiesViewModel: ExploreBuddiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreBuddiesViewModel =
            ViewModelProvider(this).get(ExploreBuddiesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore_buddies, container, false)
        val textView: TextView = root.findViewById(R.id.text_explore_buddies)
        exploreBuddiesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}