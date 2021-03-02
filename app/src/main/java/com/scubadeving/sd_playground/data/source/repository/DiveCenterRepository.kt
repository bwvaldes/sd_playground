package com.scubadeving.sd_playground.data.source.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.scubadeving.sd_playground.data.model.DiveCenter
import com.scubadeving.sd_playground.utils.TAG_FIRESTORE

/**
 * Repository that handles DiveCenter objects.
 */
class DiveCenterRepository(val firestore: FirebaseFirestore) {

//    getEcoDiveCenter {
//        Log.d(TAG_POJO, "DIVE CENTER: $it")
//    }

    private fun getAllDiveCenters() {
        firestore.collection("diveCenters")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG_FIRESTORE, "Read Dive Centers: ${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error getting documents.", exception)
            }
    }

    private fun getEcoDiveCenter(diveCenter: (DiveCenter?) -> Unit) {
        firestore.collection("diveCenters").document("3eyk7ZbWvWWFtQ71N7VA")
            .get()
            .addOnSuccessListener { result ->
                Log.d(TAG_FIRESTORE, "Read Dive Center: ${result.id} => ${result.data}")
                diveCenter(result?.toObject<DiveCenter>())
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error getting documents.", exception)
            }
    }
}
