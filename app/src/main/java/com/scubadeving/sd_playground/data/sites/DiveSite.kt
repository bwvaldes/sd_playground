package com.scubadeving.sd_playground.data.sites

import com.google.firebase.firestore.GeoPoint

data class DiveSite(
    val name: String? = null,
    val location: GeoPoint? = null,
    val rating: Double? = null,
    val difficulty: Int? = null,
    val reviews: Int? = null,
    val conditions: Conditions? = null
)
