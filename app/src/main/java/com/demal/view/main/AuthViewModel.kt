package com.demal.view.main

import com.demal.model.data.user.BaseState
import com.demal.model.data.user.entity.AuthenticationResult
import com.demal.view.core.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(private val interactor: MainInteractorRepository) :
    BaseViewModel<BaseState<AuthenticationResult>>() {

    fun getData(email: String, pass: String) {
        _mutableLiveData.value = BaseState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                _mutableLiveData.postValue(interactor.login(email, pass))
            }
        }
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(BaseState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value =
            BaseState.Success(null)
        super.onCleared()
    }


}
