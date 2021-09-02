package com.demal.model.data.app_state

import com.demal.model.data.entity.AppStateEntity

sealed class BaseState<out T : AppStateEntity> {
    data class Success<out T : AppStateEntity>(val data: T) : BaseState<T>()
    data class Error<out T : AppStateEntity>(val error: Throwable) : BaseState<T>()
    data class Message<out T : AppStateEntity>(val message: String) : BaseState<T>()
    data class Loading<out T : AppStateEntity>(val isLoading: Boolean) : BaseState<T>()
}