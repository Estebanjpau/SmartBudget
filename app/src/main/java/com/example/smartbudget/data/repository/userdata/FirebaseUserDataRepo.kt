package com.example.smartbudget.data.repository.userdata

import android.util.Log
import com.example.smartbudget.data.models.TransactionData
import com.example.smartbudget.data.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseUserDataRepo @Inject constructor() {

    suspend fun downloadUserData(): MutableList<UserData> {
        return suspendCoroutine { continuation ->
            val db = Firebase.firestore
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            val userEmail = FirebaseAuth.getInstance().currentUser?.email

            val collectionRef =
                db.collection("users")
                    .document(userEmail ?: userId.toString())
                    .collection("profile")

            val userDataList = mutableListOf<UserData>()

            collectionRef.get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val username = document.getString("username")
                        val currency = document.getString("currency")
                        val securityCode = document.getString("security_code")
                        val isEnableDarkMode = document.getBoolean("is_enable_dark_mode")
                        val createdAt = document.getLong("created_at")

                        val userData = UserData(username, currency, isEnableDarkMode, securityCode, createdAt)
                        userDataList.add(userData)

                        Log.d("FirebaseUserDataRepo", "Documento descargado: $document")
                    }

                    continuation.resume(userDataList)
                }
                .addOnFailureListener { exception ->
                    Log.e("FirebaseUserDataRepo", "Error al descargar datos: $exception")
                    continuation.resumeWithException(exception)
                }

        }
    }
}