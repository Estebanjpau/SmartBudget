package com.example.smartbudget.data.implementation

import android.util.Log
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.data.models.SubscriptionDataBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseSubscriptionDataRepoImpl @Inject constructor(){
    suspend fun downloadSubscriptionUserData(): MutableList<SubscriptionData> {
        return suspendCoroutine { continuation ->
            val db = Firebase.firestore
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            val userEmail = FirebaseAuth.getInstance().currentUser?.email

            val collectionRef = db.collection("users")
                .document(userEmail ?: userId.toString())
                .collection("subscriptions")

            val subscritionList = mutableListOf<SubscriptionData>()

            collectionRef.get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val amount = document.getDouble("amount")
                        val category = document.getString("category")
                        val background = document.getString("background")
                        val subscription = document.getString("subscription")
                        val dayBilling = document.getLong("dayBilling")
                        val textColor = document.getString("colorText")

                        val subscriptionData = SubscriptionData(amount, category, subscription, background, textColor, dayBilling)
                        subscritionList.add(subscriptionData)

                        Log.d("FirebaseDataRepository", "Documento descargado: $document")
                    }

                    continuation.resume(subscritionList)
                }
                .addOnFailureListener { exception ->
                    Log.e("FirebaseDataRepository", "Error al descargar datos: $exception")
                    continuation.resumeWithException(exception)
                }
        }
    }

    suspend fun downloadSubscriptionDataBase(): MutableList<SubscriptionDataBase> {
        return suspendCoroutine { continuation ->
            val db = Firebase.firestore

            val collectionRef = db.collection("data").document("subscription_data")
                .collection("documents")

            val subscritionList = mutableListOf<SubscriptionDataBase>()

            collectionRef.get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val amount = document.getLong("amount")
                        val category = document.getString("category")
                        val background = document.getString("background")
                        val title = document.getString("subscription")

                        val subscriptionData = SubscriptionDataBase(amount, category, title , background)
                        subscritionList.add(subscriptionData)

                        Log.d("FirebaseDataRepository", "Documento descargado: $document")
                    }

                    continuation.resume(subscritionList)
                }
                .addOnFailureListener { exception ->
                    Log.e("FirebaseDataRepository", "Error al descargar datos: $exception")
                    continuation.resumeWithException(exception)
                }
        }
    }
}