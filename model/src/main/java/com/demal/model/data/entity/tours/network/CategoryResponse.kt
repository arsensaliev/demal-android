package com.demal.model.data.entity.tours.network

import com.demal.model.data.entity.category.ICategory

data class CategoryResponse(
    override val id: Int,
    override val name: String?,
    override val iconPath: String?,
    override val createdAt: String?,
) : ICategory