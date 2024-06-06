package com.example.smartbudget.data.repository.userdata

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirebaseUserRepo {

    fun updateUserCurrency(currency: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val db = Firebase.firestore
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            val userEmail = FirebaseAuth.getInstance().currentUser?.email

            val newTransaction = db.collection("users").document(userEmail ?: userId.toString())
                .collection("userData").document("profile")

            try {
                newTransaction.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val transactionData = hashMapOf(
                            "currency" to currency
                        )

                        newTransaction.set(transactionData)
                            .addOnCompleteListener {
                                Log.d("FirebaseUserRepo", "Divisa actualizada en Firebase")
                            }
                            .addOnFailureListener {
                                Log.e("FirebaseUserRepo","Error al actualizar la divisa")
                            }
                    } else {
                        Log.e(
                            "FirebaseUserRepo",
                            "Ocurrió un error al consultar el documento"
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e(
                    "FirebaseUserRepo",
                    "Excepción al realizar la operación de red: ${e.message}"
                )
            }
        }
    }

    fun updateUserPIN(securityCode: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val db = Firebase.firestore
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            val userEmail = FirebaseAuth.getInstance().currentUser?.email

            val newTransaction = db.collection("users").document(userEmail ?: userId.toString())
                .collection("userData").document("profile")

            try {
                newTransaction.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val transactionData = hashMapOf(
                            "security_code" to securityCode
                        )

                        newTransaction.set(transactionData)
                            .addOnCompleteListener {
                                Log.d("FirebaseUserRepo", "Divisa actualizada en Firebase")
                            }
                            .addOnFailureListener {
                                Log.e("FirebaseUserRepo","Error al actualizar la divisa")
                            }
                    } else {
                        Log.e(
                            "FirebaseUserRepo",
                            "Ocurrió un error al consultar el documento"
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e(
                    "FirebaseUserRepo",
                    "Excepción al realizar la operación de red: ${e.message}"
                )
            }
        }
    }

    fun updateDarkMode(isEnabled: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {

            val db = Firebase.firestore
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            val userEmail = FirebaseAuth.getInstance().currentUser?.email

            val newTransaction = db.collection("users").document(userEmail ?: userId.toString())
                .collection("userData").document("profile")

            try {
                newTransaction.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val transactionData = hashMapOf(
                            "security_code" to isEnabled
                        )

                        newTransaction.set(transactionData)
                            .addOnCompleteListener {
                                Log.d("FirebaseUserRepo", "Divisa actualizada en Firebase")
                            }
                            .addOnFailureListener {
                                Log.e("FirebaseUserRepo","Error al actualizar la divisa")
                            }
                    } else {
                        Log.e(
                            "FirebaseUserRepo",
                            "Ocurrió un error al consultar el documento"
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e(
                    "FirebaseUserRepo",
                    "Excepción al realizar la operación de red: ${e.message}"
                )
            }
        }
    }
}