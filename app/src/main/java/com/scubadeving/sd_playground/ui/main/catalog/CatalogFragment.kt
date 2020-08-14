package com.scubadeving.sd_playground.ui.main.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.ui.adapters.recyclerview.decorations.CertLevelAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_catalog.*


class CatalogFragment : Fragment() {

    private var certLevels: List<String> = listOf("DD", "OW", "AOW", "Rescue", "Cave", "Ice", "Master")
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: CertLevelAdapter
    private lateinit var homeViewModel: CatalogViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(CatalogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_catalog, container, false)
        val textView: TextView = root.findViewById(R.id.text_catalog)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Search Catalog", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        cert_path_level_rv.layoutManager = layoutManager
        adapter =
            CertLevelAdapter(
                certLevels
            )
        cert_path_level_rv.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(
            cert_path_level_rv.context,
            layoutManager.orientation
        )
        cert_path_level_rv.addItemDecoration(dividerItemDecoration)
    }
}