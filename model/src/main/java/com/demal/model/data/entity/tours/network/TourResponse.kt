package com.demal.model.data.entity.tours.network

data class TourResponse(
    val id: Int,
    val title: String,
    val subTitle: String,
    val place: String,
    val price: Int,
    val country: String,
    val duration: Int,
    val description: String?,
    val travelersCount: Int,
    val startDate: String,
    val categoryId: Int,
    val createdAt: String,
    val images: List<ImageResponse>,
    val category: CategoryResponse,
)