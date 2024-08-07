package com.cvelez.testsesionlogin.domain.repository

import com.cvelez.testsesionlogin.core.NetworkResult
import com.cvelez.testsesionlogin.data.dto.LoginDto
import com.cvelez.testsesionlogin.data.dto.TokenDto

interface AuthRepository {
    suspend fun login(loginDto: LoginDto): NetworkResult<TokenDto>
}