package com.cvelez.testsesionlogin.core

sealed interface NetworkResult<out T> {
    val data: T?
    val message: String?

    data class Success<out T>(override val data: T) : NetworkResult<T> {
        override val message: String? = null
    }

    data class Error<out T>(override val message: String, override val data: T? = null) : NetworkResult<T>

    object Loading : NetworkResult<Nothing> {
        override val data: Nothing? = null
        override val message: String? = null
    }
}