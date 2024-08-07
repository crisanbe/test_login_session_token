package com.cvelez.testsesionlogin.core

import retrofit2.Response

abstract class BaseRepository {

    protected suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "An unknown error occurred")
        }
    }
}