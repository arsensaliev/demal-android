package com.demal.repository.types

import com.google.gson.annotations.SerializedName

enum class Order {
    @SerializedName("desc") DESCENDING,
    @SerializedName("asc") ASCENDING,
}