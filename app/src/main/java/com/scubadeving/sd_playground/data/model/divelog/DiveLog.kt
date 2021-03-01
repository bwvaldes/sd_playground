package com.scubadeving.sd_playground.data.model.divelog

import com.google.firebase.firestore.DocumentReference

data class DiveLog(
    val id: Int? = null,
    val date: String? = null,
    val diveSite: DocumentReference? = null,
    val diveType: String? = null, // Converter
    val gasType: String? = null, // Converter
    val depth: Double? = null,
    val bottomTime: Double? = null,
    val weight: Double? = null,
    val timeIn: Double? = null,
    val timeOut: Double? = null,
    val airIn: Double? = null,
    val airOut: Double? = null,
    val diveCenter: DocumentReference? = null,
    val buddies: List<Buddy>? = null,
    val notes: String? = null,
    val wildlife: List<String>? = null,
    val photos: List<String>? = null
)
