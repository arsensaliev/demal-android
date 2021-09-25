package com.demal.repository.data_sources.preferences

import android.content.SharedPreferences

class PreferencesDataSourceImpl(
    private val preferences: SharedPreferences,
    parcelableDataSource: ParcelablePreferencesDataSource,
    booleanDataSource: BooleanPreferencesDataSource
) : GeneralPreferencesDataSource,
    ParcelablePreferencesDataSource by parcelableDataSource,
    BooleanPreferencesDataSource by booleanDataSource {

    override fun remove(key: String) {
        preferences
            .edit()
            .remove(key)
            .apply()
    }
}