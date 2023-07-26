package com.example.smartbudget.domain

interface FirebaseRepository {
    fun loadTransactions(amount: Double, category: String, description: String)
    fun checkUserSession() : Boolean
}