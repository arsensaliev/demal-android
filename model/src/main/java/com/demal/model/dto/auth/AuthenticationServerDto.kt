package com.demal.model.dto.auth

import com.google.gson.annotations.SerializedName

class AuthenticationServerDto(
    @field:SerializedName("email") var email: String?,
    @field:SerializedName("password") val pass: String?
)
