package com.scubadeving.sd_playground.ui.main.inbox.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scubadeving.sd_playground.data.model.InboxMessage
import com.scubadeving.sd_playground.data.model.diver.Certification
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.databinding.FragmentInboxMessagesBinding
import com.scubadeving.sd_playground.ui.adapters.recyclerview.MessageAdapter

class MessagesFragment : Fragment() {

    private lateinit var messagesViewModel: MessagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messagesViewModel = ViewModelProvider(this).get(MessagesViewModel::class.java)
        return FragmentInboxMessagesBinding.inflate(inflater, container, false).apply {
            configureMessagesRecyclerView()
        }.root
    }

    private fun FragmentInboxMessagesBinding.configureMessagesRecyclerView() {
        val guestDiver1 = Diver(firstName = "Arnold", certifications = arrayListOf(Certification("Rescue Diver")))
        val guestDiver2 = Diver(firstName = "Jack", certifications = arrayListOf(Certification("Discover Diver")))
        val messages: ArrayList<InboxMessage> = arrayListOf(
            InboxMessage(guestDiver1, "15min ago", "This is a Message"),
            InboxMessage(guestDiver2, "Yesterday", "This is a Message")
        )
        val adapter = MessageAdapter()
        adapter.submitList(messages)
        messagesRv.adapter = adapter
    }
}
