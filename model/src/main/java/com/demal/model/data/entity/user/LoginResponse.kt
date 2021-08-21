package com.demal.model.data.entity.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val auth: Auth,
    val user: User
): Parcelable