package com.scubadeving.sd_playground.data.model

data class InboxNotification(
    val date: String,
    val data: String,
    val notificationType: Int = NOTIFICATION_TYPE_INBOX
) {
    companion object {
        const val NOTIFICATION_TYPE_DASHBOARD = 0
        const val NOTIFICATION_TYPE_INBOX = 1
    }
}
