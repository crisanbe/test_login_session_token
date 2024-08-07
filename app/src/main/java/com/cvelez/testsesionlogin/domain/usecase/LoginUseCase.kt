package com.cvelez.testsesionlogin.domain.usecase

import com.cvelez.testsesionlogin.core.NetworkResult
import com.cvelez.testsesionlogin.data.dto.LoginDto
import com.cvelez.testsesionlogin.data.dto.TokenDto
import com.cvelez.testsesionlogin.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginDto: LoginDto): NetworkResult<TokenDto> {
        return authRepository.login(loginDto)
    }
}