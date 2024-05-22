package com.example.smartbudget.domain

import com.example.smartbudget.data.RepositoryFb
import com.example.smartbudget.data.implementation.FirebaseSubscriptionRepoImpl
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.data.models.SubscriptionDataBase
import javax.inject.Inject

class SubscriptionUseCase @Inject constructor(
    private val repositoryFb: RepositoryFb
){
    fun loadSubscription(amount: Double, category: String, title: String, day:Int, background: String, colorText: String){
        repositoryFb.loadSubscription(amount, category, title, day, background, colorText)
    }

    suspend fun downloadSubscription(): MutableList<SubscriptionData> {
        return repositoryFb.downloadSubscriptionData()
    }

    suspend fun downloadSubcriptionDatabase(): MutableList<SubscriptionDataBase> {
        return repositoryFb.downloadSubscriptionDatabase()
    }
}