package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.databinding.ItemChatDetailContainerMemberBinding

class ChatMemberAdapter : ListAdapter<Diver, RecyclerView.ViewHolder>(ChatMemberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMemberViewHolder {
        return ChatMemberViewHolder(
            ItemChatDetailContainerMemberBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatMember = getItem(position)
        (holder as ChatMemberViewHolder).bind(chatMember)
    }

    inner class ChatMemberViewHolder(private val binding: ItemChatDetailContainerMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chatMember: Diver) {
            binding.apply {
                setClickListener { navigateToProfile(it, chatMember) }
                chatDetailCardDiverName.text = chatMember.firstName
                chatDetailCardDiverUsername.text = "username"
            }
        }
    }

    private fun navigateToProfile(view: View, diver: Diver) {
        val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.firstName!!)
        view.findNavController().navigate(directions)
    }

    private class ChatMemberDiffCallback : DiffUtil.ItemCallback<Diver>() {

        override fun areItemsTheSame(oldItem: Diver, newItem: Diver): Boolean {
            return oldItem.handle == newItem.handle
        }

        override fun areContentsTheSame(oldItem: Diver, newItem: Diver): Boolean {
            return oldItem == newItem
        }
    }
}
