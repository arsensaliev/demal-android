package com.demal.model.data.entity.tours

import com.demal.model.data.entity.AppStateEntity
import com.google.gson.annotations.SerializedName

data class Tour(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String? = null,
    @field:SerializedName("price") val price: Int,
    @field:SerializedName("categoryId") val categoryId: Int,
    @field:SerializedName("subTitle") val subTitle: String,
    @field:SerializedName("place") val place: String,
    @field:SerializedName("duration") val duration: Int,
    @field:SerializedName("country") val country: String,
    @field:SerializedName("travelersCount") val travelersCount: Int,
    @field:SerializedName("startDate") val startDate: String
) : AppStateEntity
