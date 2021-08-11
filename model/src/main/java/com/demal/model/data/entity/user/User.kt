package com.demal.model.data.entity.user

data class User(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val createdAt: String? = null
)