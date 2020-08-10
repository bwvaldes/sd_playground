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
import com.scubadeving.sd_playground.R

class WildlifeOverviewFragment : Fragment() {

    private lateinit var wildlifeOverviewViewModel: WildlifeOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wildlifeOverviewViewModel =
            ViewModelProvider(this).get(WildlifeOverviewViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_wildlife_overview, container, false)
        val textView: TextView = root.findViewById(R.id.text_wildlife_overview)
        wildlifeOverviewViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val toolbar: androidx.appcompat.widget.Toolbar = root.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        return root
    }


}