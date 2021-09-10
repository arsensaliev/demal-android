package com.demal.repository.interactor

import com.demal.model.data.entity.category.Categories
import com.demal.model.data.entity.category.Category
import com.demal.repository.repository.CategoryRepository

class CategoryInteractorImpl(private val categoryRepository: CategoryRepository) :
    CategoryInteractor {

    override suspend fun getCategories() =
        Categories(categoryRepository.getCategories().map { Category(it) })

    override suspend fun getCategoryById(id: Int) =
        Category(categoryRepository.getCategoryById(id))
}