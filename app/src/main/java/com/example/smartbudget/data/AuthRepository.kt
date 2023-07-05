package com.example.smartbudget.data

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Completable

class AuthRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(email: String, password: String): Completable {
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

    fun login(email: String, password: String): Completable {
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

    fun logout(): Completable {
        return Completable.create { notify ->
            firebaseAuth.signOut()
            notify.onComplete()
        }
    }
}