package com.scubadeving.sd_playground.ui.details.wildlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ItemDetailAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.WildlifeAdapter
import kotlinx.android.synthetic.main.fragment_wildlife_detail.*

class WildlifeDetailFragment : Fragment() {

    private var encounters: List<String> =
        listOf(
            "Thailand:2",
            "Phillipines:14"
        )
    private var wildLife: List<String> =
        listOf(
            "Whale Shark",
            "Mola-Mola",
            "Spotted Sting Ray",
            "Remora"
        )
    private lateinit var conditionsLayoutManager: LinearLayoutManager
    private lateinit var wildlifeLayoutManager: LinearLayoutManager
    private lateinit var conditionsAdapter: ItemDetailAdapter
    private lateinit var wildlifeDetailAdapter: WildlifeAdapter
    private lateinit var wildlifeDetailViewModel: WildlifeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeDetailViewModel = ViewModelProvider(this).get(WildlifeDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_wildlife_detail, container, false)
        val textView: TextView = root.findViewById(R.id.text_wildlife_detail)
        wildlifeDetailViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val toolbar: androidx.appcompat.widget.Toolbar = root.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureEncounters()
        configureWildlife()
    }

    private fun configureEncounters() {
        conditionsLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        encounters_rv.layoutManager = conditionsLayoutManager
        conditionsAdapter = ItemDetailAdapter(encounters)
        encounters_rv.adapter = conditionsAdapter
        val dividerItemDecoration = DividerItemDecoration(
            encounters_rv.context,
            conditionsLayoutManager.orientation
        )
        encounters_rv.addItemDecoration(dividerItemDecoration)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(encounters_rv)
    }

    private fun configureWildlife() {
        wildlifeLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        look_out_for_rv.layoutManager = wildlifeLayoutManager
        wildlifeDetailAdapter = WildlifeAdapter(wildLife)
        look_out_for_rv.adapter = wildlifeDetailAdapter
        val dividerItemDecoration = DividerItemDecoration(
            look_out_for_rv.context,
            wildlifeLayoutManager.orientation
        )
        look_out_for_rv.addItemDecoration(dividerItemDecoration)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(look_out_for_rv)
    }

}