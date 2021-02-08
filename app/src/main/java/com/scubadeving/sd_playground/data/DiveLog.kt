package com.scubadeving.sd_playground.data

data class DiveLog(
    val id: Int,
    val diveSite: String,
    val date: String,
    val rating: Double,
    val depth: String,
    val bottomTime: String
)
