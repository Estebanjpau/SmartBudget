package com.example.smartbudget.viewmodel.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authResultFirebase: AuthResultFirebase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun register(email: String, password: String) {
        authRepository.registerFb(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { authResultFirebase.handleLoginResult(Result.success(Unit))  },
                { error -> authResultFirebase.handleLoginResult(Result.failure(error)) }
            )
            .let { disposables.add(it) }
    }

    fun login(email: String, password: String) {
        authRepository.loginFb(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { authResultFirebase.handleLoginResult(Result.success(Unit)) },
                { error -> authResultFirebase.handleLoginResult(Result.failure(error)) }
            )
            .let { disposables.add(it) }
    }

    fun logout() {
        authRepository.logoutFb()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { authResultFirebase.handleLogoutResult(Result.success(Unit)) },
                { error -> authResultFirebase.handleLogoutResult(Result.failure(error)) }
            )
            .let { disposables.add(it) }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}