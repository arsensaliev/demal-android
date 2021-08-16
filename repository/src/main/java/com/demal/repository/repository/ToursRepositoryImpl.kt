package com.demal.repository.repository

import com.demal.model.data.entity.tours.Tour
import com.demal.repository.data_sources.RemoteDataSource

class ToursRepositoryImpl(private val remoteDataSource: RemoteDataSource) : ToursRepository {
    override suspend fun getTours(sortBy: String, order: String) =
        remoteDataSource.getTours(sortBy, order)

    override suspend fun getTourById(id: Int) =
        remoteDataSource.getTourById(id)

}