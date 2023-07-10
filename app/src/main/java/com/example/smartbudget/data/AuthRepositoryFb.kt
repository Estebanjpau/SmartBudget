package com.example.smartbudget.data

import com.example.smartbudget.viewmodel.auth.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Completable

class AuthRepositoryFb: AuthRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun registerFb(email: String, password: String): Completable {
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

    override fun loginFb(email: String, password: String): Completable {
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

    override fun logoutFb(): Completable {
        return Completable.create { notify ->
            firebaseAuth.signOut()
            notify.onComplete()
        }
    }
}