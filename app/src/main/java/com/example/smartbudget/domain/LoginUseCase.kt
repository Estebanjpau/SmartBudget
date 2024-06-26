package com.example.smartbudget.domain

import com.example.smartbudget.data.AuthRepositoryFb
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authrepository: AuthRepositoryFb
) {
    fun login(email: String, password: String): Completable {
        return authrepository.login(email, password)
            .andThen(Completable.fromAction { })
    }
}