package com.demal.model.data.entity.tours.network

data class ImageResponse(
    val id: Int,
    val tourId: Int,
    val imagePath: String?,
    val createdAt: String?,
)