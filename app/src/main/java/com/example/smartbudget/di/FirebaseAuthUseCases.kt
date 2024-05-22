package com.example.smartbudget.di

import com.example.smartbudget.domain.LogOutUseCase
import com.example.smartbudget.domain.LoginUseCase
import com.example.smartbudget.domain.SignUpUseCase

data class FirebaseAuthUseCases(
    val loginUseCase: LoginUseCase,
    val signupUseCase: SignUpUseCase,
    val logOutUseCase: LogOutUseCase
)
