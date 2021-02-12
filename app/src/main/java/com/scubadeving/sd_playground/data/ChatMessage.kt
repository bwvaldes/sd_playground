package com.scubadeving.sd_playground.data

data class ChatMessage(
    var content: String,
    val messageType: Int,
    var time: Long
) {
    companion object {
        const val MESSAGE_TYPE_HOST = 0
        const val MESSAGE_TYPE_GUEST = 1
    }
}
