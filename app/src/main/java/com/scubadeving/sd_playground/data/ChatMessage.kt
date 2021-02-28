package com.scubadeving.sd_playground.data

import com.scubadeving.sd_playground.data.diver.Diver

data class ChatMessage(
    var diver: Diver?,
    var content: String,
    val messageType: Int,
    var time: Long
) {
    companion object {
        const val MESSAGE_TYPE_DATE = 0
        const val MESSAGE_TYPE_HOST = 1
        const val MESSAGE_TYPE_GUEST = 2
    }
}
