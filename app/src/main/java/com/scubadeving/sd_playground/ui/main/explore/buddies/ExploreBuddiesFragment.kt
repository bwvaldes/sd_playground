package com.scubadeving.sd_playground.ui.main.explore.buddies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.data.model.diver.Certification
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.data.model.diver.Diver.Companion.VIEW_TYPE_HORIZONTAL
import com.scubadeving.sd_playground.databinding.FragmentExploreBuddiesBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.BuddyAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.GridSpacingItemDecoration
import com.scubadeving.sd_playground.utils.configureHorizontalRecyclerView

class ExploreBuddiesFragment : Fragment() {

    private lateinit var exploreBuddiesViewModel: ExploreBuddiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        exploreBuddiesViewModel = ViewModelProvider(this).get(ExploreBuddiesViewModel::class.java)
        return FragmentExploreBuddiesBinding.inflate(inflater, container, false).apply {
            configureNearbyDivers()
            configureDiveCenterDivers()
            configurePastDivers()
        }.root
    }

    private fun FragmentExploreBuddiesBinding.configureNearbyDivers() {
        val nearbyDivers: ArrayList<Diver> =
            arrayListOf(
                Diver(firstName = "Bob", certifications = arrayListOf(Certification(certificationName = "Open Water")), diverType = VIEW_TYPE_HORIZONTAL),
                Diver(firstName = "Billy", certifications = arrayListOf(Certification(certificationName = "Rescue Diver")), diverType = VIEW_TYPE_HORIZONTAL),
                Diver(firstName = "Jill", certifications = arrayListOf(Certification(certificationName = "Advanced Open Water")), diverType = VIEW_TYPE_HORIZONTAL),
                Diver(firstName = "Karen", certifications = arrayListOf(Certification(certificationName = "Open Water")), diverType = VIEW_TYPE_HORIZONTAL),
                Diver(firstName = "Molly", certifications = arrayListOf(Certification(certificationName = "Night Diver")), diverType = VIEW_TYPE_HORIZONTAL),
                Diver(firstName = "Don", certifications = arrayListOf(Certification(certificationName = "Open Water")), diverType = VIEW_TYPE_HORIZONTAL),
                Diver(firstName = "Bill", certifications = arrayListOf(Certification(certificationName = "Open Water")), diverType = VIEW_TYPE_HORIZONTAL),
                Diver(firstName = "Greg", certifications = arrayListOf(Certification(certificationName = "Open Water")), diverType = VIEW_TYPE_HORIZONTAL)
            )
        val adapter = BuddyAdapter()
        adapter.submitList(nearbyDivers)
        nearbyDiversRv.configureHorizontalRecyclerView(adapter)
    }

    private fun FragmentExploreBuddiesBinding.configureDiveCenterDivers() {
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
        val targetAdapter = BuddyAdapter()
        targetAdapter.submitList(diveCenterDivers)
        diveCenterDiversRv.apply {
            adapter = targetAdapter
            addItemDecoration(GridSpacingItemDecoration())
        }
        diveCenterDiversSeeAll.setOnClickListener {
            Toast.makeText(context, "Just Clicked Dive Center Buddies!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun FragmentExploreBuddiesBinding.configurePastDivers() {
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
        val targetAdapter = BuddyAdapter()
        targetAdapter.submitList(pastDivers)
        pastDiversRv.apply {
            adapter = targetAdapter
            addItemDecoration(GridSpacingItemDecoration())
        }
        diveCenterDiversSeeAll.setOnClickListener {
            Toast.makeText(context, "Just Clicked Past Dive Buddies!", Toast.LENGTH_SHORT).show()
        }
    }
}
