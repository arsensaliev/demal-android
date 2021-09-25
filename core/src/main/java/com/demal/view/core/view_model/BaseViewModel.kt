package com.demal.view.core.view_model

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.AppStateEntity
import com.demal.model.data.exceptions.NoAuthException
import com.demal.view.core.BaseNavigator
import kotlinx.coroutines.*

abstract class BaseViewModel<D : AppStateEntity>(
    private val navigator: BaseNavigator,
) : ViewModel() {

    protected val mStateLiveData = MutableLiveData<BaseState<D>>()
    val stateLiveData get() = mStateLiveData as LiveData<BaseState<D>>

    private val ioCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private val mainThreadCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    protected fun runOnUiThread(block: () -> Unit) {
        mainThreadCoroutineScope.launch { block() }
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected open fun cancelJob() {
        ioCoroutineScope.coroutineContext.cancelChildren()
        mainThreadCoroutineScope.coroutineContext.cancelChildren()
    }

    @CallSuper
    open fun handleError(error: Throwable) {
        if (error is NoAuthException) {
            runOnUiThread {
                navigator.toLoginScreen()
            }
        }
    }

    protected fun runAsync(block: suspend () -> Unit) =
        ioCoroutineScope.launch {
            block()
        }


    protected fun <T> runAsyncWithResult(block: suspend () -> T) =
        ioCoroutineScope.async {
            block()
        }
}
