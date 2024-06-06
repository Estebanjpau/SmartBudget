package com.example.smartbudget.data.repository.transaction

import android.util.Log
import com.example.smartbudget.ui.utils.formatters.DateUtils
import com.example.smartbudget.ui.utils.TimestampUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseTransactionRepo @Inject constructor() {

    fun loadTransactions(amount: Double, category: String, description: String) {

        CoroutineScope(Dispatchers.IO).launch {

            val db = Firebase.firestore
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            val userEmail = FirebaseAuth.getInstance().currentUser?.email

            val capturedTimestamp = TimestampUtils.getCurrentTimestamp()
            val convertTimeStamptoDate = DateUtils.convertTimestampToDateNumber(capturedTimestamp.toLong())

            val newTransaction = db.collection("users").document(userEmail ?: userId.toString())
                .collection("transaction").document(convertTimeStamptoDate)

            try {
                newTransaction.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val transactionData = hashMapOf(
                            "amount" to amount,
                            "category" to category,
                            "description" to description,
                            "timestamp" to capturedTimestamp
                        )

                        newTransaction.set(transactionData)
                            .addOnCompleteListener {
                                Log.d("FirebaseTransactionRepo", "Datos cargados en Firebase")
                            }
                            .addOnFailureListener {
                                Log.e(
                                    "FirebaseTransactionRepo",
                                    "Error al cargar datos del movimiento en Firebase"
                                )
                            }
                    } else {
                        Log.e(
                            "FirebaseTransactionRepo",
                            "Ocurrió un error al consultar el documento"
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e(
                    "FirebaseTransactionRepo",
                    "Excepción al realizar la operación de red: ${e.message}"
                )
            }
        }
    }

    fun checkUserSession(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }
}