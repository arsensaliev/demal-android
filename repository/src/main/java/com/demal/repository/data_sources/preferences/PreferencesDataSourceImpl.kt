package com.demal.repository.data_sources.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PreferencesDataSourceImpl(
    context: Context,
) : GeneralPreferencesDataSource {

    private val preferences =
        context.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)

    override fun getString(key: String) =
        preferences.getString(key, null)

    override fun putString(key: String, value: String) {
        preferences
            .edit()
            .putString(key, value)
            .apply()
    }

    override fun remove(key: String) {
        preferences
            .edit()
            .remove(key)
            .apply()
    }

    companion object {
        private const val APP_PREFERENCES = "APP_PREFERENCES"
    }
}