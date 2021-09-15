package com.demal.model.data.entity.tours

import com.demal.model.data.entity.category.Category
import com.demal.model.data.entity.tours.network.ImageResponse

data class Tour(
    override val id: Int,
    override val title: String,
    override val subTitle: String?,
    override val place: String,
    override val price: Int,
    override val country: String,
    override val duration: Int,
    override val description: String?,
    override val travelersCount: Int,
    override val startDate: String?,
    override val categoryId: Int,
    override val createdAt: String,
    override val category: Category,
    override val images: List<ImageResponse>,
) : ITour