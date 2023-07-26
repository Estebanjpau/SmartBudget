package com.example.smartbudget.domain

import com.example.smartbudget.data.models.TransactionData

interface FirebaseDataRepository {
    suspend fun downloadTransactionData(): MutableList<TransactionData>
}