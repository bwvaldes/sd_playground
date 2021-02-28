package com.scubadeving.sd_playground.data

import com.scubadeving.sd_playground.data.diver.Diver

data class InboxMessage(val diver: Diver, val date: String, val data: String)
