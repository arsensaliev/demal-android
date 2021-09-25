package com.demal.repository.data_sources.preferences

import android.content.SharedPreferences

class StringPreferencesDataSourceImpl(
    private val preferences: SharedPreferences
) : StringPreferencesDataSource {

    override fun getString(key: String) = preferences.getString(key, null)

    override fun putString(key: String, value: String?) =
        preferences.edit().putString(key, value).apply()
}