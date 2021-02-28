package com.scubadeving.sd_playground.data.wildlife

import com.google.firebase.firestore.GeoPoint

data class Wildlife(
    val commonName: String? = null,
    val scientificName: String = "Lorem Ipsum",
    val conservationStatus: ConservationStatus = ConservationStatus.LEAST_CONCERN,
    val description: String = "A beautiful creature",
    val location: GeoPoint? = null,
    val encounters: Encounters? = null
)
