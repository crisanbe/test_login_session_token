package com.cvelez.testsesionlogin.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvelez.testsesionlogin.core.NetworkResult
import com.cvelez.testsesionlogin.data.dto.LoginDto
import com.cvelez.testsesionlogin.data.dto.TokenDto
import com.cvelez.testsesionlogin.data.network.api.SessionManager
import com.cvelez.testsesionlogin.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _isSuccessLoading = MutableStateFlow(false)
    val isSuccessLoading: StateFlow<Boolean> = _isSuccessLoading.asStateFlow()

    private val _imageErrorAuth = MutableStateFlow(false)
    val imageErrorAuth: StateFlow<Boolean> = _imageErrorAuth.asStateFlow()

    private val _progressBar = MutableStateFlow(false)
    val progressBar: StateFlow<Boolean> = _progressBar.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBar.value = true
            when (val result = loginUseCase(LoginDto(email, password))) {
                is NetworkResult.Success -> handleSuccess(result)
                is NetworkResult.Error -> handleError(result)
                is NetworkResult.Loading -> _progressBar.value = true
            }
        }
    }

    private suspend fun handleSuccess(result: NetworkResult.Success<TokenDto>) {
        delay(1500L)
        _isSuccessLoading.value = true
        result.data?.let { sessionManager.saveAuthToken(it.accessTokenVerify) }
        _progressBar.value = false
    }

    private suspend fun handleError(result: NetworkResult.Error<TokenDto>) {
        _errorMessage.value = result.message
        _imageErrorAuth.value = true
        delay(1500L)
        _imageErrorAuth.value = false
        _progressBar.value = false
    }

    fun clearAuth() {
        sessionManager.clearAuthToken()
        _isSuccessLoading.value = false
        Log.d("Logging", "Auth token cleared")
    }
}