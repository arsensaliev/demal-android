package com.demal.model.data.entity.tours.domain

data class TourImage(
    val id: Int,
    val tourId: Int,
    val imagePath: String,
    val createdAt: String,
)