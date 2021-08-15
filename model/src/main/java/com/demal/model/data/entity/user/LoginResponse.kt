package com.demal.model.data.entity.user

data class LoginResponse(
    val auth: Auth,
    val user: User
)