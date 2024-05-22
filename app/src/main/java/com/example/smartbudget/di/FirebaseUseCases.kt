package com.example.smartbudget.di

import com.example.smartbudget.domain.SubscriptionUseCase
import com.example.smartbudget.domain.TransactionUseCase

data class FirebaseUseCases(
    val subscriptionUseCase: SubscriptionUseCase,
    val transactionUseCase: TransactionUseCase
)