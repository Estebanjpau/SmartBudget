package com.example.smartbudget.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthResultFirebase @Inject constructor() {
    private val _loginResult = MutableLiveData<Result<Unit>>()
    val loginResult: LiveData<Result<Unit>> get() = _loginResult

    private val _logoutResult = MutableLiveData<Result<Unit>>()
    val logoutResult: LiveData<Result<Unit>> get() = _logoutResult

    fun handleLoginResult(result: Result<Unit>) {
        _loginResult.value = result
    }

    fun handleLogoutResult(result: Result<Unit>) {
        _logoutResult.value = result
    }
}