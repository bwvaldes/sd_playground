package com.scubadeving.sd_playground.data.source.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.scubadeving.sd_playground.data.model.diver.Diver
import com.scubadeving.sd_playground.utils.TAG_FIRESTORE

/**
 * Repository that handles Diver objects.
 */
class DiverRepository(private val firestore: FirebaseFirestore) {

    fun getAllDivers(divers: (List<Diver>?) -> Unit) {
        firestore.collection("divers")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG_FIRESTORE, "Read Divers: ${document.id} => ${document.data}")
                    divers(result?.toObjects<Diver>())
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error getting documents.", exception)
            }
    }

    fun createNewDiver(diver: Diver) {
        firestore.collection("divers")
            .add(diver)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG_FIRESTORE,
                    "Create Diver: DocumentSnapshot added with ID ${documentReference.id}"
                )
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error adding document", exception)
            }
    }

    fun getDiver(diverId: String, diver: (Diver?) -> Unit) {
        firestore.collection("divers").document(diverId)
            .get()
            .addOnSuccessListener { result ->
                Log.d(TAG_FIRESTORE, "Read Diver: ${result.id} => ${result.data}")
                diver(result?.toObject<Diver>())
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error getting documents.", exception)
            }
    }

    fun updateDiver(diverId: String) {
        val diverRef = firestore.collection("divers").document(diverId)
        diverRef
            .update("firstName", "Jimmy")
            .addOnSuccessListener {
                Log.d(TAG_FIRESTORE, "Update Diver: DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error updating document", exception)
            }
        diverRef
            .update("lastName", "Chungus")
            .addOnSuccessListener {
                Log.d(TAG_FIRESTORE, "Update Diver: DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Error updating document", exception)
            }
    }

    fun deleteDiver(diverId: String) {
        firestore.collection("divers").document(diverId)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG_FIRESTORE, "Delete Diver: DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG_FIRESTORE, "Delete Diver:Error deleting document", exception)
            }
    }
}
