package com.cvelez.testsesionlogin.data.network.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Content-Type", "application/json")
            requestBuilder.addHeader("x-access-token", it)
        }
        return chain.proceed(requestBuilder.build())
    }
}