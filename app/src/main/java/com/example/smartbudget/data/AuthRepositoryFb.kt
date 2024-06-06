package com.example.smartbudget.data

import com.example.smartbudget.data.repository.auth.AuthFirebase
import com.example.smartbudget.data.repository.transaction.FirebaseTransactionRepo
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AuthRepositoryFb @Inject constructor(
    private val firebase: AuthFirebase,
    private val firebaseRepository: FirebaseTransactionRepo,
) {
    fun register(email: String, password: String, username: String): Completable {
        return firebase.registerFb(email, password, username)
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