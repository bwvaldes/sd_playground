package com.scubadeving.sd_playground.data.diver

import com.google.firebase.firestore.DocumentReference

data class Certification(
    val agencyName: String? = null,
    val catalogCertification: DocumentReference? = null,
    val certificationNumber: String? = null,
    val certificationName: String? = null,
    val diverNumber: String? = null,
    val certificationDate: String? = null,
    val instructorNumber: String? = null,
    val instructorName: String? = null,
    val diveCenter: DocumentReference? = null
)
