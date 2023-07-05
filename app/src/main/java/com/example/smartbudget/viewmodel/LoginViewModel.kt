package com.example.smartbudget.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbudget.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject  constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _navigateToHomeScreen = MutableLiveData<Unit>()
    val navigateToHomeScreen: LiveData<Unit> = _navigateToHomeScreen

    fun loginUser(username: String, password: String) {
        _loadingState.value = true

        val loginResult = loginUseCase.login(username, password)

        if (loginResult.success) {
            _navigateToHomeScreen.value = Unit
        } else {
            _errorMessage.value = loginResult.errorMessage ?: ""
        }

        _loadingState.value = false
    }
}