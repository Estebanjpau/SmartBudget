package com.example.smartbudget.data.repository.subscription

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseSubscriptionRepo @Inject constructor() {
    fun loadSubscription(amount: Double, category: String, title: String, day: Int, background: String, colorText: String) {

            CoroutineScope(Dispatchers.IO).launch {

                val db = Firebase.firestore
                val userId = FirebaseAuth.getInstance().currentUser?.uid
                val userEmail = FirebaseAuth.getInstance().currentUser?.email


                val newTransaction = db.collection("users").document(userEmail ?: userId.toString())
                    .collection("subscriptions").document(title)

                try {
                    newTransaction.get().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val transactionData = hashMapOf(
                                "amount" to amount,
                                "category" to category,
                                "subscription" to title,
                                "dayBilling" to day,
                                "background" to background,
                                "textColor" to colorText
                            )

                            newTransaction.set(transactionData)
                                .addOnCompleteListener {
                                    Log.d("FirebaseSubscriptionRepo", "Datos cargados en Firebase")
                                }
                                .addOnFailureListener {
                                    Log.e(
                                        "FirebaseSubscriptionRepo",
                                        "Error al cargar datos de la suscripcion en Firebase"
                                    )
                                }
                        } else {
                            Log.e(
                                "FirebaseSubscriptionRepo",
                                "Ocurrió un error al consultar el documento"
                            )
                        }
                    }
                } catch (e: Exception) {
                    Log.e(
                        "FirebaseSubscriptionRepo",
                        "Excepción al realizar la operación de red: ${e.message}"
                    )
                }
            }
        }

}