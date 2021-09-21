package org.romeo.feature_register

import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.EmptyEntity
import com.demal.model.data.entity.user.RegisterDto
import com.demal.model.data.entity.user.User
import com.demal.repository.repository.UserRepository
import com.demal.view.core.view_model.BaseViewModel

class RegisterViewModel(
    private val navigator: RegisterNavigator,
    private val repository: UserRepository
) : BaseViewModel<User>(navigator) {

    fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        runAsync {
            mStateLiveData.postValue(BaseState.Loading(true))
            val user = repository.register(
                RegisterDto(
                    email,
                    password,
                    firstName,
                    lastName
                )
            )

            mStateLiveData.postValue(BaseState.Success(user))
            navigator.toProfileScreen()
        }
    }

    fun navigateToLoginScreen() {
        navigator.toLoginScreen()
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}