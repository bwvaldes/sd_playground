package com.scubadeving.sd_playground.data

data class DiveSite(
    val name: String,
    val location: String,
    val rating: Double,
    val reviews: Int,
    val conditions: Conditions? = null
)

data class Conditions(
    val difficulty: String,
    val temperature: String,
    val depth: String,
    val visibility: String,
    val diveType: String
)
