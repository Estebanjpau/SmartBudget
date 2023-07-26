package com.example.smartbudget.domain

import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.data.models.SubscriptionDataBase

interface FirebaseSubscriptionDataRepo {
    suspend fun downloadSubscriptionData():MutableList<SubscriptionData>
    suspend fun downloadSubscriptionDataBase():MutableList<SubscriptionDataBase>
}