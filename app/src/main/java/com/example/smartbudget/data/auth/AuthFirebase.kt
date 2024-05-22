package com.example.smartbudget.data.auth

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AuthFirebase @Inject constructor() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerFb(email: String, password: String): Completable {
        return Completable.create { notify ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        notify.onComplete()
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