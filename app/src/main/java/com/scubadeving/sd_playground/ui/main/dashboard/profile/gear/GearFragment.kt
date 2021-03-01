package com.scubadeving.sd_playground.ui.main.dashboard.profile.gear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.GridLayoutManager.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.gear.Gear
import com.scubadeving.sd_playground.data.gear.GearProfile
import com.scubadeving.sd_playground.ui.adapters.recyclerview.GearAdapter
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.fragment_profile_gear.gear_filtered_rv
import kotlinx.android.synthetic.main.fragment_profile_gear.gear_profile_gear_list
import kotlinx.android.synthetic.main.fragment_profile_gear.gear_profiles_rv

class GearFragment : Fragment() {

    private lateinit var gearViewModel: GearViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gearViewModel = ViewModelProvider(this).get(GearViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_profile_gear, container, false)
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Add Gear", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(android.R.drawable.ic_input_add))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureGearProfileList()
        configureGearItems()
    }

    private fun configureGearProfileList() {
        val gearList = listOf(Gear("Suit"), Gear("Suit"), Gear("Suit"))
        val gearProfiles: ArrayList<GearProfile> =
            arrayListOf(
                GearProfile("Saltwater"),
                GearProfile("Freshwater"),
                GearProfile("Cold Setup"),
                GearProfile("Tropical")
            )
        gear_profiles_rv.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = GearAdapter(gearProfiles, true)
        }
        gear_profile_gear_list.text = gearList.toString()
    }

    private fun configureGearItems() {
        val gearList = listOf(Gear("Suit"), Gear("Suit"), Gear("Suit"))
        val gearProfiles: ArrayList<GearProfile> =
            arrayListOf(
                GearProfile("Saltwater"),
                GearProfile("Freshwater"),
                GearProfile("Cold Setup"),
                GearProfile("Tropical")
            )
        gear_filtered_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = GearAdapter(gearProfiles, false)
            setOnClickListener {
                Toast.makeText(context, "Just Clicked Gear Item!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
