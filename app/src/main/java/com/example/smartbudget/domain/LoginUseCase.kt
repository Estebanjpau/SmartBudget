package com.example.smartbudget.domain

import javax.inject.Inject

class LoginUseCase @Inject constructor() {
    fun login(username: String, password: String): LoginResult {

        val isValidCredentials = checkCredentials(username, password)

        if (isValidCredentials) {
            val token = generateAuthToken()

            return LoginResult(success = true, authToken = token)
        } else {
            return LoginResult(success = false, errorMessage = "Credenciales inválidas")
        }
    }

    private fun checkCredentials(username: String, password: String): Boolean {
        val validUsername = "admin"
        val validPassword = "123456"

        return (username == validUsername && password == validPassword)
    }

    private fun generateAuthToken(): String {
        return "TOKEN123"
    }
}

data class LoginResult(val success: Boolean, val authToken: String? = null, val errorMessage: String? = null)