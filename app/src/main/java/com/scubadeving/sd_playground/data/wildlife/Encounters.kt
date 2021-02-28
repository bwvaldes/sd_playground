package com.scubadeving.sd_playground.data.wildlife

import com.google.firebase.firestore.GeoPoint

data class Encounters(
    val count: Int? = null,
    val locations: List<GeoPoint>? = null
)
