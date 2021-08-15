package com.demal.model.data.entity.user

import com.demal.model.data.entity.AppStateEntity

data class User(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val country: String?,
    val city: String?,
    val imagePath: String?,
    val password: String,
    val createdAt: String? = null
) : AppStateEntity