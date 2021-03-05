package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.adapters.ViewBindingAdapter.setClickListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.data.model.InboxMessage
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.databinding.ItemInboxMessageCardBinding
import com.scubadeving.sd_playground.utils.inflate

class MessageAdapter : ListAdapter<InboxMessage, RecyclerView.ViewHolder>(MessageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ItemInboxMessageCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = getItem(position)
        (holder as MessageViewHolder).bind(message)
    }

    inner class MessageViewHolder(private val binding: ItemInboxMessageCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: InboxMessage) {
            binding.apply {
                messageCardAvatar.setOnClickListener { navigateToProfile(it, message.diver) }
                messageCardDiverName.text = message.diver.firstName
                messageCardDate.text = message.date
                messageCardData.text = message.data
                setClickListener {
                    Log.d("RecyclerView", "CLICK!")
                    Toast.makeText(
                        itemView.context,
                        "Just Clicked Message Item!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToChatDetail(it, message.diver)
                }
            }
        }

        private fun navigateToChatDetail(view: View, diver: Diver) {
            val directions = MainNavigationDirections.actionGlobalChatFragment(diver.firstName!!)
            view.findNavController().navigate(directions)
        }

        private fun navigateToProfile(view: View, diver: Diver) {
            val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.firstName!!)
            view.findNavController().navigate(directions)
        }
    }

    private class MessageDiffCallback : DiffUtil.ItemCallback<InboxMessage>() {

        override fun areItemsTheSame(oldItem: InboxMessage, newItem: InboxMessage): Boolean {
            return oldItem.diver == newItem.diver
        }

        override fun areContentsTheSame(oldItem: InboxMessage, newItem: InboxMessage): Boolean {
            return oldItem == newItem
        }
    }
}
