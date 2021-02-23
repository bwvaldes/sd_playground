package com.scubadeving.sd_playground.ui.main.inbox.messages.chat.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Diver
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ChatMemberAdapter
import kotlinx.android.synthetic.main.fragment_chat_details.chat_detail_members_rv
import kotlinx.android.synthetic.main.fragment_chat_details.chat_detail_toolbar

class ChatDetailFragment : Fragment() {

    private lateinit var chatDetailViewModel: ChatDetailViewModel
    private val args: ChatDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatDetailViewModel = ViewModelProvider(this).get(ChatDetailViewModel::class.java)
        return inflater.inflate(R.layout.fragment_chat_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chat_detail_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        val members = listOf(Diver(args.diverName, "Open Water", 2))
        chat_detail_members_rv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ChatMemberAdapter(members)
        }
    }
}
