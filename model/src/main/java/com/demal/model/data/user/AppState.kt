package com.demal.model.data.user

sealed class AppState<out T : Any> {

    data class Success<out T : Any>(val data: T?) : AppState<T>()
    data class Loading<out T : Any>(val progress: Int?) : AppState<T>()
    data class Error<out T : Any>(val error: Throwable) : AppState<T>()
}
