package com.demal.view.main

import com.demal.model.dto.auth.AppStateAuth
import com.demal.view.core.viewmodel.BaseViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(private val interactor: MainInteractorRepository) :
    BaseViewModel<AppStateAuth>() {

    fun getData(email: String, pass: String) {
        cancelJob()
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                _mutableLiveData.postValue(interactor.getAuthData(email, pass))
            }
        }
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppStateAuth.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value =
            AppStateAuth.Success(null)
        super.onCleared()
    }


}
