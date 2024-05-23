package com.example.smartbudget.domain

import com.example.smartbudget.data.AuthRepositoryFb
import com.example.smartbudget.data.RepositoryFb
import javax.inject.Inject

class ValidateSessionUseCase @Inject constructor(
    private val repositoryFb: AuthRepositoryFb,
){
    fun checkUserSession(): Boolean{
        return repositoryFb.checkSession()
    }
}