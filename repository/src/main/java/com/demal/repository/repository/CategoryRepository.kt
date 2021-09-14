package com.demal.repository.repository

import com.demal.model.data.entity.category.CategoriesResponse
import com.demal.model.data.entity.category.CategoryResponse

interface CategoryRepository {
    suspend fun getCategories(): CategoriesResponse
    suspend fun getCategoryById(id: Int): CategoryResponse
}