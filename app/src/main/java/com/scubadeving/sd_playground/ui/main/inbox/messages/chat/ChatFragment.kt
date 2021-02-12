package com.scubadeving.sd_playground.ui.main.inbox.messages.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.ChatMessage
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_DATE
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
        chat_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    private fun configureChatRecyclerView() {
        val chatMessages = mutableListOf(
            ChatMessage("Wednesday, Mar 3rd 2021 ", MESSAGE_TYPE_DATE, 1200),
            ChatMessage("Hey bud, long time no dive!", MESSAGE_TYPE_HOST, 1300),
            ChatMessage("Yea its been a minute, we should dive soon!", MESSAGE_TYPE_GUEST, 1400),
            ChatMessage("Agreed, how's this weekend sound?", MESSAGE_TYPE_HOST, 1500),
            ChatMessage("Perfect, lets do it.", MESSAGE_TYPE_GUEST, 1600),
            ChatMessage(
                "Sweet, see you at Leo Carillo on Saturday, 6am!!",
                MESSAGE_TYPE_HOST,
                1208
            ),
            ChatMessage("Saturday, Mar 6th 2021 ", MESSAGE_TYPE_DATE, 600),
            ChatMessage(
                "Hey hey, Im at the site, parked by the 2nd lighthouse. You here yet?",
                MESSAGE_TYPE_HOST,
                600
            ),
            ChatMessage(
                "Yup, parking was terrible. Walking up to you now, see you in a minute.",
                MESSAGE_TYPE_GUEST,
                600
            ),
            ChatMessage(
                "Awesome, I'll start gearing up, catch you in a few!",
                MESSAGE_TYPE_HOST,
                600
            )
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
