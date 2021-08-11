package com.demal.model.data.app_state

sealed class BaseState<out T> : State {
    data class Success<out T>(val data: T) : BaseState<T>()
    data class Error<out T>(val error: Throwable) : BaseState<T>()
    data class Message<out T>(val message: String) : BaseState<T>()
    data class Loading<out T>(val isLoading: Boolean): BaseState<T>()
}