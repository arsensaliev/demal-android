package com.demal.view.core.view_model

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.AppStateEntity
import com.demal.model.data.exceptions.NoAuthException
import com.demal.repository.data_sources.TokenRepository
import com.demal.view.core.BaseNavigator
import kotlinx.coroutines.*

abstract class BaseViewModel<D : AppStateEntity>(
    private val navigator: BaseNavigator,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val mStateLiveData = MutableLiveData<BaseState<D>>()
    val stateLiveData get() = mStateLiveData as LiveData<BaseState<D>>

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected open fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    @CallSuper
    open fun handleError(error: Throwable) {
        if (error is NoAuthException) {
            runAsync {
                tokenRepository.removeToken()
            }

            navigator.toLoginScreen()
        }
    }

    protected fun runAsync(block: suspend () -> Unit) =
        viewModelCoroutineScope.launch {
            block()
        }


    protected fun <T> runAsyncWithResult(block: suspend () -> T) =
        viewModelCoroutineScope.async {
            block()
        }
}
