package com.example.smartbudget.domain

import com.example.smartbudget.data.RepositoryFb
import com.example.smartbudget.data.implementation.FirebaseDataRepositoryImpl
import com.example.smartbudget.data.implementation.FirebaseRepositoryImpl
import com.example.smartbudget.data.implementation.FirebaseSubscriptionRepoImpl
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.data.models.SubscriptionDataBase
import com.example.smartbudget.data.models.TransactionData
import javax.inject.Inject

class TransactionUseCase @Inject constructor(
    private val repositoryFb: RepositoryFb,
){
    fun loadTransaction(amount: Double, category: String, description: String){
        repositoryFb.loadTransactions(amount, category, description)
    }

    suspend fun downloadTransactionData(): MutableList<TransactionData>{
        return repositoryFb.downloadTransactions()
    }
}