package com.scubadeving.sd_playground.data

data class DiveSite(
    val name: String,
    val location: String,
    val rating: Double,
    val reviews: Int,
    val conditions: Conditions? = null
)

data class Conditions(
    val difficulty: Int,
    val temperature: String,
    val depth: String,
    val visibility: String,
    val diveType: DiveType
)

enum class DiveType {
    TRAINING,
    BOAT,
    SHORE,
    NIGHT,
    DRIFT,
    FRESH,
    SALT
}