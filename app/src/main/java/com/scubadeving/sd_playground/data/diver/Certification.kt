package com.scubadeving.sd_playground.data.diver

import com.google.firebase.firestore.DocumentReference
import java.util.Date

data class Certification(
    val agencyName: String? = null,
    val catalogCertificationId: DocumentReference? = null,
    val certificationNumber: String? = null,
    val certificationName: String? = null,
    val diverNumber: String? = null,
    val date: Date? = null,
    val instructorNumber: String? = null,
    val instructorName: String? = null,
    val diveCenterId: String? = null
)
