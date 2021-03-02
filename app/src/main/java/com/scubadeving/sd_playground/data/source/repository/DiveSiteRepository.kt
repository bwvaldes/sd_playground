package com.scubadeving.sd_playground.data.source.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.scubadeving.sd_playground.data.model.sites.DiveSite
import com.scubadeving.sd_playground.utils.TAG_FIRESTORE

/**
 * Repository that handles DiveSite objects.
 */
class DiveSiteRepository(val firestore: FirebaseFirestore) {

    private fun getAllDiveSites() {
        firestore.collection("diveSites")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG_FIRESTORE, "Read Dive Sites: ${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error getting documents.", exception)
            }
    }

    private fun getDiveSiteCatalina(diveSite: (DiveSite?) -> Unit) {
        firestore.collection("diveSites").document("dyhJj73cKyKtptsj6wLJ")
            .get()
            .addOnSuccessListener { result ->
                Log.d(TAG_FIRESTORE, "Read Dive Site: ${result.id} => ${result.data}")
                diveSite(result?.toObject<DiveSite>())
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error getting documents.", exception)
            }
    }
}
