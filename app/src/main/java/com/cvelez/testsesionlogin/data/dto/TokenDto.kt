package com.cvelez.testsesionlogin.data.dto

import com.google.gson.annotations.SerializedName

data class TokenDto(
    @SerializedName("token") val accessTokenVerify: String,
    @SerializedName("message") val message: String
)