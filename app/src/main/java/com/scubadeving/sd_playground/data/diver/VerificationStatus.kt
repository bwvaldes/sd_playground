package com.scubadeving.sd_playground.data.diver

import com.google.firebase.firestore.DocumentReference

data class VerificationStatus(
    val isVerified: Boolean? = false,
    val verificationLevel: DocumentReference? = null
)