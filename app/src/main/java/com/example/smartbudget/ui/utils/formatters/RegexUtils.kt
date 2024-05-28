package com.example.smartbudget.ui.utils.formatters

object RegexUtils {

    fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"
        return email.matches(emailPattern.toRegex())
    }

    fun isValidPassword(input: String): Boolean {
        val lengthPattern = "^.{6,16}$"
        return input.matches(lengthPattern.toRegex())
    }

    fun isValidNickname(input: String): Boolean {
        val lengthPattern = "^.{6,24}$"
        return input.matches(lengthPattern.toRegex())
    }
}