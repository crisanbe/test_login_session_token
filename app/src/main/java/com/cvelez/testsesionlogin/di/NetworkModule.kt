package com.cvelez.testsesionlogin.di

import android.content.Context
import com.cvelez.testsesionlogin.BuildConfig
import com.cvelez.testsesionlogin.data.network.api.AuthApiService
import com.cvelez.testsesionlogin.data.network.api.AuthInterceptor
import com.cvelez.testsesionlogin.data.network.api.AuthenticateApi
import com.cvelez.testsesionlogin.data.network.api.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideSessionManager(context: Context): SessionManager = SessionManager(context)

    @Provides
    @Singleton
    fun provideBaseURL(): String = BuildConfig.API_BASE_URL

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideAuthInterceptor(sessionManager: SessionManager): AuthInterceptor =
        AuthInterceptor(sessionManager)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        authenticateApi: AuthenticateApi
    ): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(40, TimeUnit.SECONDS)
        .connectTimeout(40, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .writeTimeout(40, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .authenticator(authenticateApi)
        .build()

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        baseUrl: String, okHttpClient: OkHttpClient, converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideRestApiService(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideAuthenticateApi(sessionManager: SessionManager): AuthenticateApi =
        AuthenticateApi(sessionManager)
}