package com.demal.repository.data_sources

import com.demal.model.data.entity.tours.network.NetworkTour
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.User
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

interface RemoteDataSource {
    suspend fun login(auth: LoginRequest): LoginResponse
    suspend fun myUser(): User
    suspend fun getTours(sortBy: SortBy, order: Order): List<NetworkTour>
    suspend fun getTourById(id: Int) : NetworkTour
}