package com.demal.repository.data_sources.preferences

import android.content.SharedPreferences
import android.os.Parcelable
import com.google.gson.Gson

class ParcelablePreferencesDataSourceImpl(
    private val preferences: SharedPreferences
) : ParcelablePreferencesDataSource {

    override fun <T : Parcelable> getParcelable(key: String) =
        Gson().fromJson<T>(preferences.getString(key, null), null)

    override fun <T : Parcelable> putParcelable(key: String, value: T) {
        preferences
            .edit()
            .putString(key, Gson().toJson(value))
            .apply()
    }
}