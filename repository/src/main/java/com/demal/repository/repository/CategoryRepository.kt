package com.demal.repository.repository

import com.demal.model.data.entity.tours.network.CategoryResponse

interface CategoryRepository {
    suspend fun getCategories(): List<CategoryResponse>
    suspend fun getCategoryById(id: Int): CategoryResponse
}