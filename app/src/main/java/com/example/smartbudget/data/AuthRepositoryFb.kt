package com.example.smartbudget.data

import com.example.smartbudget.data.auth.AuthFirebase
import com.example.smartbudget.data.implementation.FirebaseRepositoryImpl
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AuthRepositoryFb @Inject constructor(
    private val firebase: AuthFirebase,
    private val firebaseRepository: FirebaseRepositoryImpl,
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

    fun checkSession(): Boolean {
        return firebaseRepository.checkUserSession()
    }

}