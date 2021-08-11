package com.demal.repository.data_sources

import com.demal.model.data.entity.user.LoginRequest
import com.demal.repository.api.ApiService

class RemoteDataSourceImpl(
    private val service: ApiService
) : RemoteDataSource {

    override suspend fun login(auth: LoginRequest) =
        service.login(auth).await()

    override suspend fun myUser() =
        service.myUser().await()
}