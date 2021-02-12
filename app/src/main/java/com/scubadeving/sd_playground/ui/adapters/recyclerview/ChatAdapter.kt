package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.ChatMessage
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_GUEST
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_HOST
import kotlinx.android.synthetic.main.item_chat_guest_container.view.*
import kotlinx.android.synthetic.main.item_chat_guest_container.view.chat_guest_avatar
import kotlinx.android.synthetic.main.item_chat_host_container.view.*
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter(var chatMessages: MutableList<ChatMessage>) :
    RecyclerView.Adapter<ChatMessageViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder<*> {
        val context = parent.context
        return when (viewType) {
            MESSAGE_TYPE_HOST -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_chat_host_container, parent, false)
                ChatHostMessageViewHolder(view)
            }
            MESSAGE_TYPE_GUEST -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_guest_container, parent, false)
                ChatGuestMessageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ChatMessageViewHolder<*>, position: Int) {
        val chatMessage = chatMessages[position]
        when (holder) {
            is ChatHostMessageViewHolder -> holder.bind(chatMessage)
            is ChatGuestMessageViewHolder -> holder.bind(chatMessage)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = chatMessages.size

    override fun getItemViewType(position: Int): Int = chatMessages[position].messageType

    fun addMessage(message: ChatMessage){
        chatMessages.add(message)
        notifyDataSetChanged()
    }

    inner class ChatHostMessageViewHolder(val view: View) : ChatMessageViewHolder<ChatMessage>(view) {

        override fun bind(message: ChatMessage) {
            itemView.apply {
                chat_host_message.text = message.content
                chat_host_message_time.text = getChatMessageTime(message)
            }
        }
    }

    inner class ChatGuestMessageViewHolder(val view: View) : ChatMessageViewHolder<ChatMessage>(view) {

        override fun bind(message: ChatMessage) {
            itemView.apply {
                chat_guest_avatar.setOnClickListener {
                    // FIXME nav with args to specific frag
                    it.findNavController().navigate(R.id.profileFragment)
                }
                chat_guest_name.text = "John Doe"
                chat_guest_message.text = message.content
                chat_guest_message_time.text = getChatMessageTime(message)
            }
        }
    }
}

abstract class ChatMessageViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(message: T)
}

fun getChatMessageTime(message: ChatMessage): String {
    val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return format.format(message.time)
}