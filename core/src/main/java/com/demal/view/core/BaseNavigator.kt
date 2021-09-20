package com.demal.view.core

//Все навигаторы должны наследоваться от этого класса
interface BaseNavigator {
    var navigationContainer: NavigationContainer?
    fun toLoginScreen()
    fun onDestroyNavigation()
    fun back()
}