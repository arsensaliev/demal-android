package com.demal.repository.repository

import com.demal.model.data.entity.category.Category
import com.demal.model.data.entity.category.network.CategoriesResponse

interface CategoryRepository {
    suspend fun getCategories(): CategoriesResponse
    suspend fun getCategoryById(id: Int): Category
}