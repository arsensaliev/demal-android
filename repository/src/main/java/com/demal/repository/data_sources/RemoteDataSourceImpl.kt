package com.demal.repository.data_sources

import com.demal.model.data.entity.tours.network.AddToWishListEntity
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.UserUpdate
import com.demal.repository.api.ApiService
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

class RemoteDataSourceImpl(
    private val service: ApiService
) : RemoteDataSource {

    override suspend fun login(auth: LoginRequest) =
        service.login(auth).await()

    override suspend fun myUser() =
        service.myUser().await()

    override suspend fun updateUser(id: Int, user: UserUpdate) =
        service.updateUser(id, user).await()

    override suspend fun updateAvatar(fileByte: ByteArray?) {

        fileByte?.let {
            val requestFile: RequestBody = RequestBody.create(MediaType.parse("image/jpeg"), it)
            val body = MultipartBody.Part.createFormData("image", "image.jpg", requestFile)
            return service.updateAvatar(body).await()
        }

    }

    override suspend fun getTours(sortBy: SortBy, order: Order) =
        service.getTours(sortBy, order).await()

    override suspend fun getTourById(id: Int) =
        service.getTourById(id).await()

    override suspend fun getCategories() =
        service.getCategories().await()

    override suspend fun getCategoryById(id: Int) =
        service.getCategoryById(id).await()

    override suspend fun getUserWishList(id: Int) =
        service.getUserWishList(id).await()

    override suspend fun addToWishList(
        id: Int,
        wish: AddToWishListEntity
    ) = service.addToWishList(id, wish).await()?.tour

    override suspend fun deleteFromWishList(
        userId: Int,
        tourId: Int
    ) = service.deleteFromWishList(userId, tourId).await()
}