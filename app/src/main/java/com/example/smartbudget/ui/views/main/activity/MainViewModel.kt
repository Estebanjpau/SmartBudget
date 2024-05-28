package com.example.smartbudget.ui.views.main.activity

import androidx.lifecycle.ViewModel
import com.example.smartbudget.di.FirebaseAuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val firebaseAuthUseCases: FirebaseAuthUseCases
) : ViewModel() {

    fun checkIfUserIsLogged(): Boolean {
        return firebaseAuthUseCases.validateSessionUseCase.checkUserSession()
    }
}