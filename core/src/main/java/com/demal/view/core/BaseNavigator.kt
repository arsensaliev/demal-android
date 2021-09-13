package com.demal.view.core

//Все навигаторы должны наследоваться от этого класса
interface BaseNavigator {
    fun toLoginScreen()
    fun back()
}