package com.scubadeving.sd_playground.ui.main.inbox.messages.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.ChatMessage
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_DATE
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_GUEST
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_HOST
import com.scubadeving.sd_playground.data.diver.Certification
import com.scubadeving.sd_playground.data.diver.Diver
import com.scubadeving.sd_playground.ui.adapters.recyclerview.ChatAdapter
import kotlinx.android.synthetic.main.fragment_chat.chat_message_rv
import kotlinx.android.synthetic.main.fragment_chat.chat_toolbar

class ChatFragment : Fragment() {

    private lateinit var chatViewModel: ChatViewModel
    private val args: ChatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureChatRecyclerView()
        chat_toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            title = args.diverName
            subtitle = "yesterday at 22:00"
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_details -> {
                        navigateToChatDetail()
                    }
                }
                true
            }
        }
    }

    private fun Toolbar.navigateToChatDetail() {
        val directions = ChatFragmentDirections.actionChatFragmentToChatDetailsFragment(args.diverName)
        findNavController().navigate(directions)
    }

    private fun configureChatRecyclerView() {
        val guestDiver = Diver(args.diverName, certifications = arrayListOf(Certification(certificationName = "Advanced Open Water")))
        val hostDiver = Diver(firstName = "Brian", certifications = arrayListOf(Certification(certificationName = "Advanced Open Water")))
        val chatMessages = mutableListOf(
            ChatMessage(null, "Wednesday, Mar 3rd 2021 ", MESSAGE_TYPE_DATE, 1200),
            ChatMessage(hostDiver, "Hey bud, long time no dive!", MESSAGE_TYPE_HOST, 1300),
            ChatMessage(
                guestDiver,
                "Yea its been a minute, we should dive soon!",
                MESSAGE_TYPE_GUEST,
                1400
            ),
            ChatMessage(hostDiver, "Agreed, how's this weekend sound?", MESSAGE_TYPE_HOST, 1500),
            ChatMessage(guestDiver, "Perfect, lets do it.", MESSAGE_TYPE_GUEST, 1600),
            ChatMessage(
                hostDiver,
                "Sweet, see you at Leo Carillo on Saturday, 6am!!",
                MESSAGE_TYPE_HOST,
                1208
            ),
            ChatMessage(null, "Saturday, Mar 6th 2021 ", MESSAGE_TYPE_DATE, 600),
            ChatMessage(
                hostDiver,
                "Hey hey, Im at the site, parked by the 2nd lighthouse. You here yet?",
                MESSAGE_TYPE_HOST,
                600
            ),
            ChatMessage(
                guestDiver,
                "Yup, parking was terrible. Walking up to you now, see you in a minute.",
                MESSAGE_TYPE_GUEST,
                600
            ),
            ChatMessage(
                hostDiver,
                "Awesome, I'll start gearing up, catch you in a few!",
                MESSAGE_TYPE_HOST,
                600
            )
        )
        chat_message_rv.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false).apply {
                stackFromEnd = true
                isSmoothScrollbarEnabled = true
            }
            adapter = ChatAdapter(chatMessages)
        }
    }
}
