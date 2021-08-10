package com.demal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demal.di.injectDependencies
import com.demal.model.data.user.AppState
import com.demal.model.data.user.entity.AuthenticationResult
import com.demal.view.main.AuthViewModel
import kotlinx.coroutines.*
import org.koin.android.scope.currentScope

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniViewModel()
    }

    private fun iniViewModel() {
        injectDependencies()

        val authViewModel: AuthViewModel by currentScope.inject()
        authViewModel.subscribe().observe(this@MainActivity, { renderData(it) })
        authViewModel.getData("vladimir@mail.ru", "password")
    }

    private fun renderData(appState: AppState<AuthenticationResult>) {

    }


}