package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_chat_detail_container_member_.view.chat_detail_card_diver_name
import kotlinx.android.synthetic.main.item_chat_detail_container_member_.view.chat_detail_card_diver_username

class ChatMemberAdapter(
    private val chatMembers: List<Diver>
) : RecyclerView.Adapter<ChatMemberAdapter.ChatMemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMemberViewHolder {
        val inflatedView = parent.inflate(R.layout.item_chat_detail_container_member_, false)
        return ChatMemberViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = chatMembers.size

    override fun onBindViewHolder(holder: ChatMemberViewHolder, position: Int) {
        val member = chatMembers[position]
        holder.bind(member)
    }

    inner class ChatMemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(chatMember: Diver) {
            itemView.apply {
                setOnClickListener { navigateToProfile(it, chatMember) }
                chat_detail_card_diver_name.text = chatMember.firstName
                chat_detail_card_diver_username.text = "username"
            }
        }
    }

    private fun navigateToProfile(view: View, diver: Diver) {
        val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.firstName!!)
        view.findNavController().navigate(directions)
    }
}
