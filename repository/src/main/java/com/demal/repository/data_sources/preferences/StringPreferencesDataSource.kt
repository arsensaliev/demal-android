package com.demal.repository.data_sources.preferences

interface StringPreferencesDataSource {
    fun getString(key: String): String?
    fun putString(key: String, value: String)
}