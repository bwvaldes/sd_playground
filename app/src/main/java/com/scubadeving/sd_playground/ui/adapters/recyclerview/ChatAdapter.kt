package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.data.model.ChatMessage
import com.scubadeving.sd_playground.data.model.ChatMessage.Companion.MESSAGE_TYPE_DATE
import com.scubadeving.sd_playground.data.model.ChatMessage.Companion.MESSAGE_TYPE_GUEST
import com.scubadeving.sd_playground.data.model.ChatMessage.Companion.MESSAGE_TYPE_HOST
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.databinding.ItemChatContainerDateBinding
import com.scubadeving.sd_playground.databinding.ItemChatContainerGuestBinding
import com.scubadeving.sd_playground.databinding.ItemChatContainerHostBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ChatAdapter : ListAdapter<ChatMessage, RecyclerView.ViewHolder>(ChatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            MESSAGE_TYPE_DATE -> {
                ChatDateViewHolder(
                    ItemChatContainerDateBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            MESSAGE_TYPE_HOST -> {
                ChatHostMessageViewHolder(
                    ItemChatContainerHostBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            MESSAGE_TYPE_GUEST -> {
                ChatGuestMessageViewHolder(
                    ItemChatContainerGuestBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatMessage = getItem(position)
        when (holder) {
            is ChatDateViewHolder -> holder.bind(chatMessage)
            is ChatHostMessageViewHolder -> holder.bind(chatMessage)
            is ChatGuestMessageViewHolder -> holder.bind(chatMessage)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).messageType

    inner class ChatDateViewHolder(private val binding: ItemChatContainerDateBinding) :
        BaseViewHolder<ChatMessage>(binding.root) {

        override fun bind(chatMessage: ChatMessage) {
            binding.chatDateMessage.text = chatMessage.content.plus(getChatMessageTime(chatMessage))
        }
    }

    inner class ChatHostMessageViewHolder(private val binding: ItemChatContainerHostBinding) :
        BaseViewHolder<ChatMessage>(binding.root) {

        override fun bind(chatMessage: ChatMessage) {
            binding.apply {
                chatHostMessage.text = chatMessage.content
                chatHostMessageTime.text = getChatMessageTime(chatMessage)
            }
        }
    }

    inner class ChatGuestMessageViewHolder(private val binding: ItemChatContainerGuestBinding) :
        BaseViewHolder<ChatMessage>(binding.root) {

        override fun bind(chatMessage: ChatMessage) {
            binding.apply {
                chatGuestAvatar.setOnClickListener { navigateToProfile(it, chatMessage.diver!!) }
                chatGuestName.text = chatMessage.diver?.firstName
                chatGuestMessage.text = chatMessage.content
                chatGuestMessageTime.text = getChatMessageTime(chatMessage)
            }
        }

        private fun navigateToProfile(view: View, diver: Diver) {
            val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.firstName!!)
            view.findNavController().navigate(directions)
        }
    }

    fun getChatMessageTime(message: ChatMessage): String {
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(message.time)
    }

    fun addMessage(message: ChatMessage) {
        currentList.add(message)
    }

    private class ChatDiffCallback : DiffUtil.ItemCallback<ChatMessage>() {

        override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem.diver == newItem.diver
        }

        override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem == newItem
        }
    }
}
