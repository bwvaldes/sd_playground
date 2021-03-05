package com.scubadeving.sd_playground.data.model.sites

import com.google.firebase.firestore.GeoPoint

data class DiveSite(
    val name: String? = null,
    val location: GeoPoint? = null,
    val rating: Double? = null,
    val difficulty: Int? = null,
    val reviews: Int? = null,
    val conditions: Conditions? = null,
    val viewType: Int = VIEW_TYPE_EXPANDED
) {
    companion object {
        const val VIEW_TYPE_SMALL = 0
        const val VIEW_TYPE_EXPANDED = 1
    }
}
