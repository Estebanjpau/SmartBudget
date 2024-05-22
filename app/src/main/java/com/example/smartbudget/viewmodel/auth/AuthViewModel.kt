package com.example.smartbudget.viewmodel.auth

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

    private val _navigateToHomeScreen = MutableLiveData<Unit>()
    val navigateToHomeScreen: LiveData<Unit> = _navigateToHomeScreen

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private val disposables = CompositeDisposable()

    fun register(email: String, password: String) {
        _loadingState.postValue(true)

        firebaseAuthUseCases.signupUseCase.signup(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    authResultFirebase.handleLoginResult(Result.success(Unit))
                    _navigateToHomeScreen.postValue(Unit)
                    _loadingState.postValue(false)
                },
                { error ->
                    authResultFirebase.handleLoginResult(Result.failure(error))
                    _loadingState.postValue(false)
                }
            )
            .let { disposables.add(it) }
    }

    fun login(email: String, password: String) {
        _loadingState.postValue(true)

            firebaseAuthUseCases.loginUseCase.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    authResultFirebase.handleLoginResult(Result.success(Unit))
                    _navigateToHomeScreen.postValue(Unit)
                    _loadingState.postValue(false)
                },
                { error ->
                    authResultFirebase.handleLoginResult(Result.failure(error))
                    _loadingState.postValue(false)
                }
            )
            .let { disposables.add(it) }
    }

    fun logout() {
        firebaseAuthUseCases.logOutUseCase.logout()
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