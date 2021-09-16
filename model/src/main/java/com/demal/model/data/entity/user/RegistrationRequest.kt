package com.demal.model.data.entity.user

data class RegistrationRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)
