package com.example.smartbudget.di

import com.example.smartbudget.data.implementation.FirebaseDataRepositoryImpl
import com.example.smartbudget.domain.FirebaseDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseTransactionModule {

    @Provides
    fun provideFirebaseDataRepository(impl: FirebaseDataRepositoryImpl): FirebaseDataRepository {
        return impl
    }
}