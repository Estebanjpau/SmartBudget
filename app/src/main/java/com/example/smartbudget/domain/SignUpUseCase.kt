package com.example.smartbudget.domain

import com.example.smartbudget.data.AuthRepositoryFb
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authrepository: AuthRepositoryFb
){
    fun signup(email: String, password: String, username: String): Completable {
        return authrepository.register(email, password, username)
    }
}