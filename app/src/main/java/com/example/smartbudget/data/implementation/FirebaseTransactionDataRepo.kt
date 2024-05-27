package com.example.smartbudget.data.implementation

import android.util.Log
import com.example.smartbudget.data.models.TransactionData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseTransactionDataRepo @Inject constructor() {

    suspend fun downloadTransactionData(): MutableList<TransactionData> {
        return suspendCoroutine { continuation ->
            val db = Firebase.firestore
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            val userEmail = FirebaseAuth.getInstance().currentUser?.email

            val collectionRef =
                db.collection("users")
                .document(userEmail ?: userId.toString())
                .collection("transaction")

            val transactionList = mutableListOf<TransactionData>()

            collectionRef.get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val amount = document.getDouble("amount")
                        val category = document.getString("category")
                        val description = document.getString("description")
                        val timestamp = document.getLong("timestamp")

                        val transactionData = TransactionData(amount, category, description, timestamp)
                        transactionList.add(transactionData)

                        Log.d("FirebaseDataRepository", "Documento descargado: $document")
                    }

                    continuation.resume(transactionList)
                }
                .addOnFailureListener { exception ->
                    Log.e("FirebaseDataRepository", "Error al descargar datos: $exception")
                    continuation.resumeWithException(exception)
                }
        }
    }
}