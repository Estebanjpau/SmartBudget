package com.example.smartbudget.di.modules

import com.example.smartbudget.data.AuthRepositoryFb
import com.example.smartbudget.di.FirebaseAuthUseCases
import com.example.smartbudget.domain.LogOutUseCase
import com.example.smartbudget.domain.LoginUseCase
import com.example.smartbudget.domain.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseAuthRepository {

    @Singleton
    @Provides
    fun provideFirebaseUseCases(authRepository: AuthRepositoryFb) = FirebaseAuthUseCases(
        LoginUseCase(authRepository),
        SignUpUseCase(authRepository),
        LogOutUseCase(authRepository)
    )
}