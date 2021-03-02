package com.scubadeving.sd_playground.data.source.repository

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.scubadeving.sd_playground.data.model.gear.Gear
import com.scubadeving.sd_playground.utils.TAG_FIRESTORE

/**
 * Repository that handles Gear objects.
 */
class GearRepository(val firestore: FirebaseFirestore) {

//    getGearProfiles { gearProfiles ->
//        Log.d("GEAR PROFILE RESULT", "$gearProfiles")
//        gearProfiles?.forEach { profile ->
//            profile.gearList?.forEach {
//                getGearFromGearProfiles(it) { gear ->
//                    Log.d(TAG_POJO, "GEAR ITEM: $gear")
//                }
//            }
//        }
//    }

    private fun getGearFromGearProfiles(docRef: DocumentReference, gear: (Gear?) -> Unit) {
        firestore.document(docRef.path)
            .get()
            .addOnSuccessListener { result ->
                Log.d(TAG_FIRESTORE, "GET GEAR FROM PROFILE RESULT: $result")

                gear(result?.toObject<Gear>())
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error adding document", exception)
            }
    }
}
