package com.example.smartbudget.data.models

data class SubscriptionData(
    val amount: Double?,
    val category: String?,
    val subscription: String?,
    val background: String?,
    val textColor: String?,
    val dayBilling: Long?
)

data class SubscriptionDataBase(
    val amount: Long?,
    val category: String?,
    val title: String?,
    val background: String?
)