package com.demal.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.demal.R
import com.demal.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val navigatorHolder: NavigatorHolder by inject()
    private val viewModel: MainViewModel by viewModel()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        initNavigation()

        viewModel.init()
    }

    private fun initNavigation() {
        navigatorHolder.setNavigator(
            AppNavigator(this, R.id.main_fragment_container)
        )

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> viewModel.toHomeScreen()
                R.id.nav_tours -> viewModel.toToursScreen()
                R.id.nav_my_tours -> viewModel.toMyToursScreen()
                R.id.nav_wishlist -> viewModel.toWishlistScreen()
                R.id.nav_profile -> viewModel.toProfileScreen()
            }

            true
        }

        viewModel.getBottomNavigationLiveData().observe(this) { isShown ->
            if (isShown) showBottomNavigation()
            else hideBottomNavigation()
        }
    }

    private fun hideBottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        showBottomNavigation()
        super.onBackPressed()
    }
}