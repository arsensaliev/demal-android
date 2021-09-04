package com.demal.model.data.entity.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Auth(
    val token: String
) : Parcelable