package com.scubadeving.sd_playground.ui.main.explore.buddies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.diver.Certification
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.ui.adapters.recyclerview.BuddyAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_explore_buddies.dive_center_divers_rv
import kotlinx.android.synthetic.main.fragment_explore_buddies.dive_center_divers_see_all
import kotlinx.android.synthetic.main.fragment_explore_buddies.nearby_divers_rv
import kotlinx.android.synthetic.main.fragment_explore_buddies.past_divers_rv
import kotlinx.android.synthetic.main.fragment_explore_buddies.past_divers_see_all

class ExploreBuddiesFragment : Fragment() {

    private lateinit var exploreBuddiesViewModel: ExploreBuddiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreBuddiesViewModel = ViewModelProvider(this).get(ExploreBuddiesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_explore_buddies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureNearbyDivers()
        configureDiveCenterDivers()
        configurePastDivers()
    }

    private fun configureNearbyDivers() {
        val nearbyDivers: ArrayList<Diver> =
            arrayListOf(
                Diver(firstName = "Bob", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Billy", certifications = arrayListOf(Certification(certificationName = "Rescue Diver"))),
                Diver(firstName = "Jill", certifications = arrayListOf(Certification(certificationName = "Advanced Open Water"))),
                Diver(firstName = "Karen", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Molly", certifications = arrayListOf(Certification(certificationName = "Night Diver"))),
                Diver(firstName = "Don", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Bill", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Greg", certifications = arrayListOf(Certification(certificationName = "Open Water")))
            )
        nearby_divers_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = BuddyAdapter(nearbyDivers, true)
            val dividerItemDecoration =
                DividerItemDecoration(context, HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun configureDiveCenterDivers() {
        val diveCenterDivers: ArrayList<Diver> =
            arrayListOf(
                Diver(firstName = "Jill", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Jack", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Pedro", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Nick", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Jill", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Karen", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Molly", certifications = arrayListOf(Certification(certificationName = "Open Water")))
            )
        dive_center_divers_rv.apply {
            adapter = BuddyAdapter(diveCenterDivers, false)
            addItemDecoration(GridSpacingItemDecoration())
        }
        dive_center_divers_see_all.setOnClickListener {
            Toast.makeText(context, "Just Clicked Dive Center Buddies!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configurePastDivers() {
        val pastDivers: ArrayList<Diver> =
            arrayListOf(
                Diver(firstName = "Lia", certifications = arrayListOf(Certification(certificationName = "Discover Diver"))),
                Diver(firstName = "Arnold", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Richard", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Brandon", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Jill", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Jack", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Pedro", certifications = arrayListOf(Certification(certificationName = "Open Water"))),
                Diver(firstName = "Nick", certifications = arrayListOf(Certification(certificationName = "Open Water")))
            )
        past_divers_rv.apply {
            adapter = BuddyAdapter(pastDivers, false)
            addItemDecoration(GridSpacingItemDecoration())
        }
        past_divers_see_all.setOnClickListener {
            Toast.makeText(context, "Just Clicked Past Dive Buddies!", Toast.LENGTH_SHORT).show()
        }
    }
}
