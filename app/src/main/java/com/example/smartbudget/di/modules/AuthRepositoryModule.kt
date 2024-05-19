package com.example.smartbudget.di.modules

import com.example.smartbudget.data.AuthRepositoryFb
import com.example.smartbudget.viewmodel.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule {

    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryFb()
    }
}