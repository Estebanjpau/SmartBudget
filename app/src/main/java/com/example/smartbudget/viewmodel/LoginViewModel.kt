package com.example.smartbudget.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbudget.di.SessionManager
import com.example.smartbudget.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _navigateToHomeScreen = MutableLiveData<Unit>()
    val navigateToHomeScreen: LiveData<Unit> = _navigateToHomeScreen

    private var loginDisposable: Disposable? = null

    fun loginUser(username: String, password: String) {
        _loadingState.value = true

        loginDisposable?.dispose()

        loginDisposable = loginUseCase.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) // Cambiado a hilo principal
            .subscribe(
                {
                    SessionManager.isLoggedIn = true
                    _navigateToHomeScreen.postValue(Unit) // Cambiado a postValue
                    _loadingState.postValue(false) // Cambiado a postValue
                },
                { error ->
                    _errorMessage.postValue(error.message ?: "An error occurred") // Cambiado a postValue
                    _loadingState.postValue(false) // Cambiado a postValue
                }
            )
    }

    override fun onCleared() {
        loginDisposable?.dispose()
        super.onCleared()
    }
}
