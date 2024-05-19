package com.example.smartbudget.domain

import com.example.smartbudget.viewmodel.auth.AuthRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    fun login(email: String, password: String): Completable {
        return authRepository.loginFb(email, password)
            .andThen(Completable.fromAction { })
            .onErrorComplete()
    }
}