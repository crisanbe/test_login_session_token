package com.cvelez.testsesionlogin.data.network.api

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthenticateApi @Inject constructor(
    private val sessionManager: SessionManager
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? = runBlocking {
        sessionManager.fetchAuthToken()?.let {
            response.request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-access-token", it)
                .build()
        }
    }
}