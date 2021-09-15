package com.demal.repository.repository

import com.demal.model.data.entity.category.Categories
import com.demal.model.data.entity.category.Category

interface CategoryRepository {
    suspend fun getCategories(): Categories
    suspend fun getCategoryById(id: Int): Category
}