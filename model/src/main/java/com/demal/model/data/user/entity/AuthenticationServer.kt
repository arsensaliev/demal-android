package com.demal.model.data.user.entity

import com.google.gson.annotations.SerializedName

class AuthenticationServer(
    @field:SerializedName("email") var email: String?,
    @field:SerializedName("password") val pass: String?
)
