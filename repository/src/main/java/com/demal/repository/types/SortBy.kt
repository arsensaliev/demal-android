package com.demal.repository.types

import com.google.gson.annotations.SerializedName

enum class SortBy {
    @SerializedName("createdAt")
    CREATED_AT,
    @SerializedName("id")
    ID,
    @SerializedName("place")
    PLACE,
    @SerializedName("price")
    PRICE,
    @SerializedName("country")
    COUNTRY,
    @SerializedName("duration")
    DURATION,
    @SerializedName("travelersCount")
    TRAVELERS_COUNT,
    @SerializedName("startDate")
    START_DATE,
    @SerializedName("categoryId")
    CATEGORY_ID,
}