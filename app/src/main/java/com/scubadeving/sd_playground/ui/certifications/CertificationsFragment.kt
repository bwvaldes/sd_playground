package com.scubadeving.sd_playground.ui.certifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.decorations.GridSpacingItemDecoration
import com.scubadeving.sd_playground.ui.adapters.CertCardAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_certifications.*

class CertificationsFragment : Fragment() {

    var certCards: List<String> = listOf("One", "Two", "Three", "Four", "Five", "Six", "Seven")
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: CertCardAdapter
    private lateinit var notificationsViewModel: CertificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(CertificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_certifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_certifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        activity?.fab?.setOnClickListener {
            Toast.makeText(activity, "Search My Certs", Toast.LENGTH_SHORT).show()
        }
        activity?.fab?.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spanCount = 2
        val spacing = 15
        val includeEdge = true
        layoutManager = GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false)
        cert_card_rv.layoutManager = layoutManager
        adapter = CertCardAdapter(certCards)
        cert_card_rv.adapter = adapter
        cert_card_rv.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
    }
}