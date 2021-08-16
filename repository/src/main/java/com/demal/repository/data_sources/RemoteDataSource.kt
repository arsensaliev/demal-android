package com.demal.repository.data_sources

import com.demal.model.data.entity.tours.Tour
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.User

interface RemoteDataSource {
    suspend fun login(auth: LoginRequest): LoginResponse
    suspend fun myUser(): User
    suspend fun getTours(sortBy: String, order: String): List<Tour>
    suspend fun getTourById(id: Int) : Tour
}