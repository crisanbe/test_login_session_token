package com.cvelez.testsesionlogin.data.network.api

import com.cvelez.testsesionlogin.data.dto.LoginDto
import com.cvelez.testsesionlogin.data.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/signin")
    suspend fun getLogin(@Body loginDto: LoginDto) : Response<TokenDto>
}