package com.cvelez.testsesionlogin.data.repository

import com.cvelez.testsesionlogin.core.BaseRepository
import com.cvelez.testsesionlogin.core.NetworkResult
import com.cvelez.testsesionlogin.data.dto.LoginDto
import com.cvelez.testsesionlogin.data.dto.TokenDto
import com.cvelez.testsesionlogin.data.network.api.AuthApiService
import com.cvelez.testsesionlogin.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository, BaseRepository() {

    override suspend fun login(loginDto: LoginDto): NetworkResult<TokenDto> {
        return safeApiCall { authApiService.getLogin(loginDto) }
    }
}