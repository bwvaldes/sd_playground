package com.scubadeving.sd_playground.ui.main.saved.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.DiveSite
import com.scubadeving.sd_playground.data.SavedList
import com.scubadeving.sd_playground.ui.adapters.recyclerview.DiveSiteAdapter
import com.scubadeving.sd_playground.ui.adapters.recyclerview.SavedListAdapter
import com.scubadeving.sd_playground.ui.details.certifications.CertificationDetailFragmentArgs
import kotlinx.android.synthetic.main.fragment_saved.*
import kotlinx.android.synthetic.main.fragment_saved_detail.*

class SavedDetailFragment : Fragment() {

    private lateinit var savedDetailViewModel: SavedDetailViewModel
    private val args: SavedDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedDetailViewModel =
            ViewModelProvider(this).get(SavedDetailViewModel::class.java)
        return inflater.inflate(R.layout.fragment_saved_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saved_detail_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        saved_detail_toolbar.title = args.savedListName
    }
}