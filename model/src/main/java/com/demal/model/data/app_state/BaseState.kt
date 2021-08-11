package com.demal.model.data.app_state

sealed class BaseState<out T : Any> : State {
    data class Success<out T : Any>(val data: T) : BaseState<T>()
    data class Loading<out T : Any>(val progress: Boolean) : BaseState<T>()
    data class Error<out T : Any>(val error: Throwable) : BaseState<T>()
    data class Message<out T : Any>(val message: String) : BaseState<T>()
}