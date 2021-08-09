package com.demal.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demal.R
import com.demal.main.view_model.MainViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val navigatorHolder: NavigatorHolder by inject()
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigatorHolder.setNavigator(
            AppNavigator(this, R.id.main_fragment_container)
        )

        viewModel.init()
    }
}