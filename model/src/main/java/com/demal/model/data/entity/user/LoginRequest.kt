package com.demal.model.data.entity.user

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @field:SerializedName("email") var email: String,
    @field:SerializedName("password") val pass: String
)
