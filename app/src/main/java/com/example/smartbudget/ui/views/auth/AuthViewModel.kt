package com.example.smartbudget.ui.views.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbudget.di.FirebaseAuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuthUseCases: FirebaseAuthUseCases,
    val authResultFirebase: AuthResultFirebase
) : ViewModel() {

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private val disposables = CompositeDisposable()

    fun register(email: String, password: String, username: String) {
        if (_loadingState.value != true) {

            _loadingState.postValue(true)

            firebaseAuthUseCases.signupUseCase.signup(email, password, username)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    {
                        authResultFirebase.handleRegisterResult(Result.success(Unit))
                        _loadingState.postValue(false)
                    },
                    { error ->
                        authResultFirebase.handleRegisterResult(Result.failure(error))
                        _loadingState.postValue(false)
                    }
                )
                .let { disposables.add(it) }
        }
    }

    fun login(email: String, password: String) {
        if (_loadingState.value != true) {

            _loadingState.postValue(true)

            firebaseAuthUseCases.loginUseCase.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        authResultFirebase.handleLoginResult(Result.success(Unit))
                        _loadingState.postValue(false)
                    },
                    { error ->
                        authResultFirebase.handleLoginResult(Result.failure(error))
                        _loadingState.postValue(false)
                    }
                )
                .let { disposables.add(it) }
        }
    }

    fun logout() {
        if (_loadingState.value != true) {

            _loadingState.postValue(true)

            firebaseAuthUseCases.logOutUseCase.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        authResultFirebase.handleLogoutResult(Result.success(Unit))
                        _loadingState.postValue(false)
                    },
                    { error ->
                        authResultFirebase.handleLogoutResult(Result.failure(error))
                        _loadingState.postValue(false)
                    }
                )
                .let { disposables.add(it) }
        }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}