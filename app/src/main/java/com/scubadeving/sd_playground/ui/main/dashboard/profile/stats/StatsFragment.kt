package com.scubadeving.sd_playground.ui.main.dashboard.profile.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.R
import kotlinx.android.synthetic.main.activity_main.fab

class StatsFragment : Fragment() {

    private lateinit var statsViewModel: StatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statsViewModel =
            ViewModelProvider(this).get(StatsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile_stats, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Search My Stats", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_action_search))
        return root
    }
}
