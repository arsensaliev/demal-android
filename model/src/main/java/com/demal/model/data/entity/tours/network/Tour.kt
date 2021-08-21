package com.demal.model.data.entity.tours.network

import com.demal.model.data.entity.tours.ITour

data class Tour(
    override val id: Int,
    override val title: String?,
    override val subTitle: String?,
    override val place: String?,
    override val price: Int,
    override val country: String?,
    override val duration: Int,
    override val description: String?,
    override val travelersCount: Int,
    override val startDate: String?,
    override val categoryId: Int,
    override val createdAt: String?,
    override val category: CategoryResponse,
    override val images: List<ImageResponse>,
): ITour