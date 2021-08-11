package com.demal.view.core.view_model

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.exceptions.NoAuthException
import com.demal.view.core.BaseNavigator
import kotlinx.coroutines.*

abstract class BaseViewModel<D>(
    private val navigator: BaseNavigator
) : ViewModel() {

    private val mStateLiveData = MutableLiveData<BaseState<D>>()
    val stateLiveData get() = mStateLiveData as LiveData<BaseState<D>>

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    @CallSuper
    open fun handleError(error: Throwable) {
        if (error is NoAuthException) {
            navigator.toLoginScreen()
            //TODO: Delete token from shared preferences
        }
    }
}
