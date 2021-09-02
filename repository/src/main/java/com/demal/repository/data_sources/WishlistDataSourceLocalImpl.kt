package com.demal.repository.data_sources

import com.demal.model.data.entity.tours.Tour

class WishlistDataSourceLocalImpl : WishlistDataSourceLocal {

    private var wishlist: MutableList<Tour>? = null

    override suspend fun tours() = wishlist

    override suspend fun delete(tourId: Int) {
        wishlist?.removeIf { tour ->
            tour.id == tourId
        }
    }

    override suspend fun add(tour: Tour) {
        createIfNotExists()
        wishlist?.add(tour)
    }

    override suspend fun deleteAll() {
        wishlist?.clear()
    }

    override suspend fun addAll(tours: List<Tour>) {
        createIfNotExists()
        wishlist?.addAll(tours)
    }

    private fun createIfNotExists() {
        wishlist = wishlist ?: mutableListOf()
    }
}