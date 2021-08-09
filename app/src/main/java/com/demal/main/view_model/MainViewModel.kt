package com.demal.main.view_model

import androidx.lifecycle.ViewModel
import com.demal.navigation.MainActivityNavigator

class MainViewModel(
    private val navigator: MainActivityNavigator
) : ViewModel() {

    fun init() {
        navigator.toProfile() //TODO: Navigate to first fragment
    }
}