package com.example.smartbudget.domain

import com.example.smartbudget.data.AuthRepositoryFb
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val authrepository: AuthRepositoryFb
){
    fun logout(): Completable {
        return authrepository.logout()
    }
}