package com.demal.repository.data_sources.preferences

interface BooleanPreferencesDataSource {
    fun getBoolean(key: String): Boolean?
    fun putBoolean(key: String, value: Boolean)
}