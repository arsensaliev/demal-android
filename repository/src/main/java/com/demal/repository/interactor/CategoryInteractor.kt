package com.demal.repository.interactor

import com.demal.model.data.entity.category.Categories
import com.demal.model.data.entity.category.Category

interface CategoryInteractor {
    suspend fun getCategories(): Categories
    suspend fun getCategoryById(id: Int): Category
}