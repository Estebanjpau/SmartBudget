package com.example.smartbudget.di.modules

import com.example.smartbudget.data.implementation.FirebaseSubscriptionDataRepoImpl
import com.example.smartbudget.data.implementation.FirebaseSubscriptionRepoImpl
import com.example.smartbudget.domain.FirebaseSubscriptionDataRepo
import com.example.smartbudget.domain.FirebaseSubscriptionRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseSubscriptionModule {
    @Provides
    fun provideFirebaseSubscriptionRepo(): FirebaseSubscriptionRepo {
        return FirebaseSubscriptionRepoImpl()
    }

    @Provides
    fun providesFirebaseSubscrioptionData(impl: FirebaseSubscriptionDataRepoImpl): FirebaseSubscriptionDataRepo{
        return impl
    }
}