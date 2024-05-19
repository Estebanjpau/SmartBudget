package com.example.smartbudget.di.modules

import com.example.smartbudget.di.FirebaseUseCases
import com.example.smartbudget.domain.LoginUseCase
import com.example.smartbudget.viewmodel.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseRepository {

    @Singleton
    @Provides
    fun provideFirebaseUseCases(authRepository: AuthRepository) = FirebaseUseCases(
        LoginUseCase(authRepository)
    )
}