package com.demal.model.data.entity.tours.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddToWishListEntity(
    val tourId: Int
) : Parcelable