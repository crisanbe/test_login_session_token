package com.cvelez.testsesionlogin.data.network.api

import android.content.Context
import android.content.SharedPreferences
import com.cvelez.testsesionlogin.R
import javax.inject.Inject

class SessionManager @Inject constructor(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String) {
        prefs.edit().putString(USER_TOKEN, token).apply()
    }

    fun fetchAuthToken(): String? = prefs.getString(USER_TOKEN, null)

    fun clearAuthToken() {
        prefs.edit().clear().apply()
    }
}