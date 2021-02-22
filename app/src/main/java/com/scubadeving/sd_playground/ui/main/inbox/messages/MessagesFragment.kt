package com.scubadeving.sd_playground.ui.main.inbox.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Diver
import com.scubadeving.sd_playground.data.InboxMessage
import com.scubadeving.sd_playground.ui.adapters.recyclerview.MessageAdapter
import kotlinx.android.synthetic.main.fragment_inbox_messages.*

class MessagesFragment : Fragment() {

    private lateinit var messagesViewModel: MessagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messagesViewModel = ViewModelProvider(this).get(MessagesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_inbox_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureMessagesRecyclerView()
    }

    private fun configureMessagesRecyclerView() {
        val guestDiver1 = Diver("Arnold", "Rescue Diver", 13)
        val guestDiver2 = Diver("Jack", "Discover Diver", 1)
        val messages: ArrayList<InboxMessage> = arrayListOf(
            InboxMessage(guestDiver1, "15min ago", "This is a Message"),
            InboxMessage(guestDiver2, "Yesterday", "This is a Message")
        )
        messages_rv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MessageAdapter(messages)
        }
    }
}
