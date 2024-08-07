package com.cvelez.testsesionlogin.di

import com.cvelez.testsesionlogin.data.repository.AuthRepositoryImpl
import com.cvelez.testsesionlogin.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository = authRepositoryImpl
}
