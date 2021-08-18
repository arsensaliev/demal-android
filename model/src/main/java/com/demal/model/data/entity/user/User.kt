package com.demal.model.data.entity.user

import com.demal.model.data.entity.AppStateEntity

data class User(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val country: String? = null,
    val city: String? = null,
    val imagePath: String? = null,
    val createdAt: String? = null
) : AppStateEntity