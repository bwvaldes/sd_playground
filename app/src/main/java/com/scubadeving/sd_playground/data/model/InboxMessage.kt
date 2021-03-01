package com.scubadeving.sd_playground.data.model

import com.scubadeving.sd_playground.data.model.diver.Diver

data class InboxMessage(val diver: Diver, val date: String, val data: String)
