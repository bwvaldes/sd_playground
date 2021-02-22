package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.ChatMessage
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_DATE
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_GUEST
import com.scubadeving.sd_playground.data.ChatMessage.Companion.MESSAGE_TYPE_HOST
import com.scubadeving.sd_playground.data.Diver
import kotlinx.android.synthetic.main.item_chat_container_date.view.*
import kotlinx.android.synthetic.main.item_chat_container_guest.view.*
import kotlinx.android.synthetic.main.item_chat_container_host.view.*
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter(var chatMessages: MutableList<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.ChatMessageViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder<*> {
        val context = parent.context
        return when (viewType) {
            MESSAGE_TYPE_DATE -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_chat_container_date, parent, false)
                ChatDateViewHolder(view)
            }
            MESSAGE_TYPE_HOST -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_chat_container_host, parent, false)
                ChatHostMessageViewHolder(view)
            }
            MESSAGE_TYPE_GUEST -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_container_guest, parent, false)
                ChatGuestMessageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(viewHolder: ChatMessageViewHolder<*>, position: Int) {
        val chatMessage = chatMessages[position]
        when (viewHolder) {
            is ChatDateViewHolder -> viewHolder.bind(chatMessage)
            is ChatHostMessageViewHolder -> viewHolder.bind(chatMessage)
            is ChatGuestMessageViewHolder -> viewHolder.bind(chatMessage)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = chatMessages.size

    override fun getItemViewType(position: Int): Int = chatMessages[position].messageType

    inner class ChatDateViewHolder(val view: View) : ChatMessageViewHolder<ChatMessage>(view) {

        override fun bind(message: ChatMessage) {
            itemView.apply {
                chat_date_message.text = message.content.plus(getChatMessageTime(message))
            }
        }
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
                chat_guest_avatar.setOnClickListener { navigateToProfile(it, message.diver!!) }
                chat_guest_name.text = "John Doe"
                chat_guest_message.text = message.content
                chat_guest_message_time.text = getChatMessageTime(message)
            }
        }

        private fun navigateToProfile(it: View, diver: Diver) {
            val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.name)
            it.findNavController().navigate(directions)
        }
    }

    abstract class ChatMessageViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(message: T)
    }

    fun addMessage(message: ChatMessage){
        chatMessages.add(message)
        notifyDataSetChanged()
    }

    fun getChatMessageTime(message: ChatMessage): String {
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(message.time)
    }
}