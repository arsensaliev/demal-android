package com.demal.repository.image

interface ImageLoader<T> {
    fun loadImage(
        loadingCode: Int? = null,
        target: T,
        url: String,
        listener: ImageLoadingListener? = null
    )
}