package com.scubadeving.sd_playground.data.gear

import com.google.firebase.firestore.DocumentReference

data class GearProfile(
    val name: String? = null,
    val gearList: List<DocumentReference>? = null
)
