package com.example.smartbudget.data

import com.example.smartbudget.data.auth.AuthFirebase
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AuthRepositoryFb @Inject constructor(
    private val firebase: AuthFirebase
) {
    fun register(email: String, password: String): Completable {
        return firebase.registerFb(email, password)
    }

    fun login(email: String, password: String): Completable {
        return firebase.loginFb(email, password)
    }

    fun logout(): Completable {
        return firebase.logoutFb()
        }

}