package com.example.smartbudget.data.repository.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AuthFirebase @Inject constructor() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerFb(email: String, password: String, username: String): Completable {
        val firebaseAuth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()

        return Completable.create { notify ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        user?.let {
                            val userEmail = user.email ?: email
                            val userMap = mapOf(
                                "username" to username,
                                "email" to userEmail
                            )
                            firestore.collection("users")
                                .document(userEmail)
                                .collection("userData")
                                .document("profile")
                                .set(userMap)
                                .addOnSuccessListener {
                                    notify.onComplete()
                                }
                                .addOnFailureListener { e ->
                                    notify.onError(e)
                                }
                        } ?: notify.onError(Exception("User is null after registration"))
                    } else {
                        val exception = task.exception
                        notify.onError(exception ?: Exception("Error al registrar"))
                    }
                }
        }
    }

    fun loginFb(email: String, password: String): Completable {
        return Completable.create { notify ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        notify.onComplete()
                    } else {
                        val exception = task.exception
                        notify.onError(exception ?: Exception("Error al iniciar sesion"))
                    }
                }
        }
    }

    fun logoutFb(): Completable {
        return Completable.create { notify ->
            firebaseAuth.signOut()
            notify.onComplete()
        }
    }
}