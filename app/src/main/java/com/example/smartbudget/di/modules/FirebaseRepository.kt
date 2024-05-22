package com.example.smartbudget.di.modules

import com.example.smartbudget.data.RepositoryFb
import com.example.smartbudget.di.FirebaseUseCases
import com.example.smartbudget.domain.SubscriptionUseCase
import com.example.smartbudget.domain.TransactionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object FirebaseRepository {
    @Module
    @InstallIn(SingletonComponent::class)
    object FirebaseAuthRepository {

        @Singleton
        @Provides
        fun provideFirebaseUseCases(repositoryFb: RepositoryFb) = FirebaseUseCases(
            SubscriptionUseCase(repositoryFb),
            TransactionUseCase(repositoryFb),
        )
    }
}