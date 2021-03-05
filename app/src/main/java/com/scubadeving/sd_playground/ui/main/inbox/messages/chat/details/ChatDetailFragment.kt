package com.scubadeving.sd_playground.ui.main.inbox.messages.chat.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.scubadeving.sd_playground.data.model.diver.Certification
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.databinding.FragmentChatDetailsBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ChatMemberAdapter

class ChatDetailFragment : Fragment() {

    private lateinit var chatDetailViewModel: ChatDetailViewModel
    private val args: ChatDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatDetailViewModel = ViewModelProvider(this).get(ChatDetailViewModel::class.java)
        return FragmentChatDetailsBinding.inflate(inflater, container, false).apply {
            chatDetailToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            val members = listOf(Diver(args.diverName, certifications = arrayListOf(Certification(certificationName = "Open Water"))))
            val adapter = ChatMemberAdapter()
            adapter.submitList(members)
            chatDetailMembersRv.adapter = adapter
        }.root
    }
}
