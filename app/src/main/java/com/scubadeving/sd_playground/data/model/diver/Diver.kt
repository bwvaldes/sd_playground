package com.scubadeving.sd_playground.data.model.diver

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.GeoPoint
import com.scubadeving.sd_playground.data.model.divelog.Buddy
import com.scubadeving.sd_playground.data.model.divelog.DiveLog
import com.scubadeving.sd_playground.data.model.gear.Gear
import com.scubadeving.sd_playground.data.model.gear.GearProfile

data class Diver(
    val avatarUrl: String? = null,
    val handle: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val dob: String? = null, // Converter
    val phoneNumber: String? = null,
    val email: String? = null,
    val location: GeoPoint? = null,
    val deviceId: String? = null,
    val photoUrl: String? = null,
    val verificationStatus: VerificationStatus? = null,
    val certifications: List<Certification>? = null,
    val diveCenters: List<DocumentReference>? = null,
    val gear: List<Gear>? = null,
    val gearProfiles: List<GearProfile>? = null,
    val stats: Stats? = null,
    val about: About? = null,
    val diveLogs: List<DiveLog>? = null,
    val buddies: List<Buddy>? = null,
//    val future : ???
    val diverType: Int = VIEW_TYPE_VERTICAL
) {
    companion object {
        const val VIEW_TYPE_HORIZONTAL = 0
        const val VIEW_TYPE_VERTICAL = 1
    }
}
