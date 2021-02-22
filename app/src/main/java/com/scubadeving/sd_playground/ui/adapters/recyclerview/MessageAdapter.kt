package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.MainNavigationDirections
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.Diver
import com.scubadeving.sd_playground.data.InboxMessage
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_message_card.view.*

class MessageAdapter(private val messages: ArrayList<InboxMessage>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflatedView = parent.inflate(R.layout.item_message_card, false)
        return MessageViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message, position)
    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(message: InboxMessage, position: Int) {
            itemView.apply {
                message_card_avatar.setOnClickListener { navigateToProfile(it, message.diver) }
                message_card_diver_name.text = message.diver.name
                message_card_date.text = message.date
                message_card_data.text = message.data
                setOnClickListener {
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

        private fun navigateToChatDetail(it: View, diver: Diver) {
            val directions = MainNavigationDirections.actionGlobalChatFragment(diver.name)
            it.findNavController().navigate(directions)
        }

        private fun navigateToProfile(it: View, diver: Diver) {
            val directions = MainNavigationDirections.actionGlobalProfileFragment(diver.name)
            it.findNavController().navigate(directions)
        }
    }
}