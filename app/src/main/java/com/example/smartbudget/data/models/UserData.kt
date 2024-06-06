package com.example.smartbudget.data.models

data class UserData(
    val currency: String?,
    val nickname: String?,
    val isEnableDarkMode: Boolean?,
    val securityCode: String?,
    val createdAt: Long?
)
