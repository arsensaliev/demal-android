package com.demal.repository.data_sources.preferences

import android.content.SharedPreferences

class BooleanPreferencesDataSourceImpl(
    private val preferences: SharedPreferences
) : BooleanPreferencesDataSource {

    override fun getBoolean(key: String): Boolean? {
        if (!preferences.contains(key)) return null

        return preferences.getBoolean(key, false)
    }

    override fun putBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }
}