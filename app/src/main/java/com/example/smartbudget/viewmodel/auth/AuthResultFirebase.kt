package com.example.smartbudget.viewmodel.auth

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartbudget.di.SessionManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthResultFirebase @Inject constructor() {
    private val _loginResult = MutableLiveData<Result<Unit>>()
    val loginResult: LiveData<Result<Unit>> get() = _loginResult

    private val _registerResult = MutableLiveData<Result<Unit>>()
    val registerResult: LiveData<Result<Unit>> get() = _registerResult

    private val _logoutResult = MutableLiveData<Result<Unit>>()
    val logoutResult: LiveData<Result<Unit>> get() = _logoutResult

    fun handleLoginResult(result: Result<Unit>) {
        _loginResult.postValue(result)

        if (result.isSuccess) {
            SessionManager.isLoggedIn = true
        } else {
            SessionManager.isLoggedIn = false
            Log.e(TAG, "handleLoginResult: ${result.exceptionOrNull()?.message} : Login Failed")
        }
    }

    fun handleRegisterResult(result: Result<Unit>) {
        _registerResult.postValue(result)

        if (result.isSuccess) {
            SessionManager.isLoggedIn = true
        } else {
            SessionManager.isLoggedIn = false
            Log.e(TAG, "handleRegisterResult: ${result.exceptionOrNull()?.message} : Register Failed")
        }
    }

    fun handleLogoutResult(result: Result<Unit>) {
        _logoutResult.value = result

        if (result.isSuccess) {
            SessionManager.isLoggedIn = false
        } else {
            SessionManager.isLoggedIn = true
            Log.e(TAG, "handleRegisterResult: ${result.exceptionOrNull()?.message} : Logout Failed")
        }
    }
}