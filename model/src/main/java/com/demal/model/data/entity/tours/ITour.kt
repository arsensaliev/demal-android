package com.demal.model.data.entity.tours

import com.demal.model.data.entity.tours.network.CategoryResponse
import com.demal.model.data.entity.tours.network.ImageResponse

interface ITour {
    val id: Int
    val title: String?
    val subTitle: String?
    val place: String?
    val price: Int
    val country: String?
    val duration: Int
    val description: String?
    val travelersCount: Int
    val startDate: String?
    val categoryId: Int
    val createdAt: String?
    val category: CategoryResponse
    val images: List<ImageResponse>
}