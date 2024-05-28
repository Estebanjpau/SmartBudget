package com.example.smartbudget.ui.views.main.profile

import androidx.lifecycle.ViewModel
import com.example.smartbudget.di.FirebaseAuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseAuthUseCases: FirebaseAuthUseCases
) : ViewModel() {

}