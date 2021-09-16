package org.romeo.feature_registration

import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.EmptyEntity
import com.demal.model.data.entity.user.RegistrationRequest
import com.demal.repository.repository.UserRepository
import com.demal.view.core.view_model.BaseViewModel

class RegistrationViewModel(
    private val navigator: RegistrationNavigator,
    private val repository: UserRepository
) : BaseViewModel<EmptyEntity>(navigator) {

    fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        runAsync {
            mStateLiveData.postValue(BaseState.Loading(true))
            repository.register(
                RegistrationRequest(
                    email,
                    password,
                    firstName,
                    lastName
                )
            )

            navigator.toLoginScreen()
        }
    }

    fun openLogin() {
        navigator.toLoginScreen()
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}