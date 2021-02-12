package com.scubadeving.sd_playground.ui.adapters.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.scubadeving.sd_playground.R
import com.scubadeving.sd_playground.data.InboxMessage
import com.scubadeving.sd_playground.data.Specialty
import com.scubadeving.sd_playground.utils.inflate
import kotlinx.android.synthetic.main.item_cert_card.view.cert_card_text
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

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            Toast.makeText(
                itemView.context,
                "Just Clicked Message Item!",
                Toast.LENGTH_SHORT
            ).show()
            view.findNavController().navigate(R.id.chatFragment)
        }

        fun bind(message: InboxMessage, position: Int) {
            itemView.apply {
                message_card_date.text = message.date
                message_card_data.text = message.data
            }
        }
    }
}