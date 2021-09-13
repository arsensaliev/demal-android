package com.demal.repository.repository

import com.demal.model.data.entity.category.Categories
import com.demal.model.data.entity.category.Category
import com.demal.repository.data_sources.RemoteDataSource

class CategoryRepositoryImpl(private val remoteDataSource: RemoteDataSource) : CategoryRepository {

    override suspend fun getCategories() =
        remoteDataSource.getCategories()

    override suspend fun getCategoryById(id: Int) =
        remoteDataSource.getCategoryById(id)
}