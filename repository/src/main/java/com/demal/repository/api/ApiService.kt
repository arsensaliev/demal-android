package com.demal.repository.api

import com.demal.model.data.entity.category.CategoriesResponse
import com.demal.model.data.entity.category.CategoryResponse
import com.demal.model.data.entity.tours.Tour
import com.demal.model.data.entity.tours.network.*
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.LoginResponse
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ApiService {

    @POST("api/$API_VERSION/users/login")
    fun login(@Body auth: LoginRequest): Deferred<LoginResponse>

    @GET("api/$API_VERSION/users/me")
    fun myUser(): Deferred<MeResponse>

    @GET("api/$API_VERSION/tours")
    fun getTours(
        @Query("sortBy") sortBy: SortBy,
        @Query("order") order: Order,
    ): Deferred<List<Tour>>

    @GET("api/$API_VERSION/tours/{id}")
    fun getTourById(
        @Path("id") id: Int,
    ): Deferred<Tour>

    @GET("api/$API_VERSION/categories")
    fun getCategories(): Deferred<CategoriesResponse>

    @GET("api/$API_VERSION/categories/{id}")
    fun getCategoryById(
        @Path("id") id: Int,
    ): Deferred<CategoryResponse>

    @GET("api/$API_VERSION/users/{id}/wishlist")
    fun getUserWishList(
        @Path("id") id: Int
    ): Deferred<Tours>

    @POST("api/$API_VERSION/users/{id}/wishlist")
    fun addToWishList(
        @Path("id") id: Int,
        @Body wish: AddToWishListEntity
    ): Deferred<AddToWishListResponse?>

    @DELETE("api/$API_VERSION/users/{uid}/wishlist/{tourId}/")
    fun deleteFromWishList(
        @Path("uid") uid: Int,
        @Path("tourId") tourId: Int
    ): Deferred<Unit>

    companion object {
        private const val API_VERSION = "v1"
    }
}
