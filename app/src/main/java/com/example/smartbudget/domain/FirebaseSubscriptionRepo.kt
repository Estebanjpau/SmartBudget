package com.example.smartbudget.domain

interface FirebaseSubscriptionRepo {
    fun loadSubscription(amount: Double, category: String, title: String, day:Int, background: String, colorText: String)
}