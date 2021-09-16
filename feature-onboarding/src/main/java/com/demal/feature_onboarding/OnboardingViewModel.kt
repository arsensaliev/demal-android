package com.demal.feature_onboarding

import com.demal.feature_onboarding.navigation.OnboardingNavigator
import com.demal.model.data.entity.AppStateEntity
import com.demal.view.core.view_model.BaseViewModel

class OnboardingViewModel(
    private val navigator: OnboardingNavigator
) : BaseViewModel<AppStateEntity>(navigator) {

    fun openHome() {
        navigator.toHomeScreen()
    }
}
