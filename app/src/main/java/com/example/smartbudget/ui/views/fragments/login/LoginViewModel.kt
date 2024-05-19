package com.example.smartbudget.ui.views.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbudget.di.FirebaseUseCases
import com.example.smartbudget.di.SessionManager
import com.example.smartbudget.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseUseCases: FirebaseUseCases
) : ViewModel() {

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

        loginDisposable = firebaseUseCases.loginUseCase.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    SessionManager.isLoggedIn = true
                    _navigateToHomeScreen.postValue(Unit)
                    _loadingState.postValue(false)
                },
                { error ->
                    _errorMessage.postValue(error.message ?: "An error occurred")
                    _loadingState.postValue(false)
                }
            )
    }

    override fun onCleared() {
        loginDisposable?.dispose()
        super.onCleared()
    }
}
