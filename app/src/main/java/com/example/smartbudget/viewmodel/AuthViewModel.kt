package com.example.smartbudget.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbudget.data.AuthRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<Result<Unit>>()
    val loginResult: LiveData<Result<Unit>> get() = _loginResult

    private val _logoutResult = MutableLiveData<Result<Unit>>()
    val logoutResult: LiveData<Result<Unit>> get() = _logoutResult

    private val disposables = CompositeDisposable()

    fun register(email: String, password: String) {
        authRepository.register(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { _loginResult.value = Result.success(Unit) },
                { error -> _loginResult.value = Result.failure(error) }
            )
            .let { disposables.add(it) }
    }

    fun login(email: String, password: String) {
        authRepository.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { _loginResult.value = Result.success(Unit) },
                { error -> _loginResult.value = Result.failure(error) }
            )
            .let { disposables.add(it) }
    }

    fun logout() {
        authRepository.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { _logoutResult.value = Result.success(Unit) },
                { error -> _logoutResult.value = Result.failure(error) }
            )
            .let { disposables.add(it) }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}