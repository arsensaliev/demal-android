package com.demal.model.data.entity.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUpdate(
    val firstName: String,
    val lastName: String,
    val country: String? = null,
    val city: String? = null,
) : Parcelable