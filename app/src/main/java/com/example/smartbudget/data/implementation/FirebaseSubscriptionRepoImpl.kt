package com.example.smartbudget.data.implementation

import android.util.Log
import com.example.smartbudget.domain.FirebaseSubscriptionRepo
import com.example.smartbudget.ui.utils.DateUtils
import com.example.smartbudget.ui.utils.TimestampUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseSubscriptionRepoImpl @Inject constructor() : FirebaseSubscriptionRepo {
    override fun loadSubscription(amount: Double, category: String, title: String, day: Int, background: String, textColor: String) {

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
                                "textColor" to textColor
                            )

                            newTransaction.set(transactionData)
                                .addOnCompleteListener {
                                    Log.d("FirebaseSubscriptionRepoImpl", "Datos cargados en Firebase")
                                }
                                .addOnFailureListener {
                                    Log.e(
                                        "FirebaseSubscriptionRepoImpl",
                                        "Error al cargar datos de la suscripcion en Firebase"
                                    )
                                }
                        } else {
                            Log.e(
                                "FirebaseSubscriptionRepoImpl",
                                "Ocurrió un error al consultar el documento"
                            )
                        }
                    }
                } catch (e: Exception) {
                    Log.e(
                        "FirebaseSubscriptionRepoImpl",
                        "Excepción al realizar la operación de red: ${e.message}"
                    )
                }
            }
        }

}