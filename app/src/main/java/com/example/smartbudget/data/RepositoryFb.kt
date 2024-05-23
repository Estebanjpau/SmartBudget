package com.example.smartbudget.data

import com.example.smartbudget.data.implementation.FirebaseDataRepositoryImpl
import com.example.smartbudget.data.implementation.FirebaseRepositoryImpl
import com.example.smartbudget.data.implementation.FirebaseSubscriptionDataRepoImpl
import com.example.smartbudget.data.implementation.FirebaseSubscriptionRepoImpl
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.data.models.SubscriptionDataBase
import com.example.smartbudget.data.models.TransactionData
import javax.inject.Inject

class RepositoryFb @Inject constructor(
    private val firebaseDataRepository: FirebaseDataRepositoryImpl,
    private val firebaseRepository: FirebaseRepositoryImpl,
    private val firebaseSubscriptionRepo: FirebaseSubscriptionRepoImpl,
    private val firebaseSubscriptionDataRepo: FirebaseSubscriptionDataRepoImpl
) {
    suspend fun getTransactions(): MutableList<TransactionData> {
        return firebaseDataRepository.downloadTransactionData()
    }

    fun loadTransactions(amount: Double, category: String, description: String) {
        firebaseRepository.loadTransactions(amount, category, description)
    }

    fun loadSubscription(amount: Double, category: String, title: String, day: Int, background: String, textColor: String) {
        firebaseSubscriptionRepo.loadSubscription(amount, category, title, day, background, textColor)
    }

    suspend fun getSubscriptionUserData(): MutableList<SubscriptionData> {
        return firebaseSubscriptionDataRepo.downloadSubscriptionUserData()
    }

    suspend fun getSubscriptionDatabase(): MutableList<SubscriptionDataBase> {
        return firebaseSubscriptionDataRepo.downloadSubscriptionDataBase()
    }
}