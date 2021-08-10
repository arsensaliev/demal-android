package com.demal.model.data.user

sealed class BaseState<out T : Any>:State {

    data class Success<out T : Any>(val data: T?) : BaseState<T>()
    data class Loading<out T : Any>(val progress: Int?) : BaseState<T>()
    data class Error<out T : Any>(val error: Throwable) : BaseState<T>()

}
interface State
