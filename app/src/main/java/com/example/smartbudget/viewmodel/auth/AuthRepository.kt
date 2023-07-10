package com.example.smartbudget.viewmodel.auth

import io.reactivex.rxjava3.core.Completable

interface AuthRepository {
    fun registerFb(email: String, password: String): Completable
    fun loginFb(email: String, password: String): Completable
    fun logoutFb(): Completable
}