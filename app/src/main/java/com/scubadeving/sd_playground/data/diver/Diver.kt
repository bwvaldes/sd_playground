package com.scubadeving.sd_playground.data.diver

import android.location.Location
import com.google.firebase.firestore.GeoPoint
import com.scubadeving.sd_playground.data.DiveCenter
import com.scubadeving.sd_playground.data.divelog.DiveLog
import com.scubadeving.sd_playground.data.gear.GearProfile

data class Diver(
    val id: String? = null,
    val avatarUrl: String? = null,
    val handle: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val age: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val location: GeoPoint? = null,
    val deviceId: String? = null,
    val imageUrl: String? = null,
    val verificationStatus: VerificationStatus? = null,
    val certifications: List<Certification>? = null,
    val diveCenter: DiveCenter? = null,
    val gear: List<GearProfile>? = null,
    val stats: Stats? = null,
    val about: About? = null,
    val diveLogs: List<DiveLog>? = null,
    val buddies: List<Diver>? = null
//    val future : ???
)
