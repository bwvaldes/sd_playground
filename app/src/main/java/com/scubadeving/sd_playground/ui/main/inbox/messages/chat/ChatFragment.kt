package com.scubadeving.sd_playground.ui.main.inbox.messages.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.ChatMessage
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_GUEST
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_HOST
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ChatAdapter
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {

    private lateinit var chatViewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureChatRecyclerView()
    }

    private fun configureChatRecyclerView() {
        val chatMessages = mutableListOf(
            ChatMessage("Hey bud, long time no dive!", MESSAGE_TYPE_HOST, 1200),
            ChatMessage("Yea its been a minute, we should dive soon!", MESSAGE_TYPE_GUEST, 1202),
            ChatMessage("Agreed, how's this weekend sound?", MESSAGE_TYPE_HOST, 1203),
            ChatMessage("Perfect, lets do it.", MESSAGE_TYPE_GUEST, 1205),
            ChatMessage("Sweet, see you at Leo Carillo on Saturday, 6am!!", MESSAGE_TYPE_HOST, 1208)
        )
        chat_message_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
                .apply {
                    stackFromEnd = true
                    isSmoothScrollbarEnabled = true
                }
            adapter = ChatAdapter(chatMessages)
        }
    }
}
