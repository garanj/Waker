package com.garan.waker

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


fun isWakerEnabled(context: Context) = runBlocking {
    context.dataStore.data
        .map { settings ->
            settings[booleanPreferencesKey(ENABLED_KEY)] ?: false
        }.first()
}

fun setWakerEnabled(context: Context, enabled: Boolean) = runBlocking {
    Log.i(TAG, "Setting Waker enabled: $enabled")
    context.dataStore.edit { settings ->
        settings[booleanPreferencesKey(ENABLED_KEY)] = enabled
    }
}

fun getWakeupInterval(context: Context) = runBlocking {
    context.dataStore.data
        .map { settings ->
            settings[intPreferencesKey(INTERVAL_SECONDS_KEY)] ?: DEFAULT_INTERVAL_SECONDS
        }.first()
}

fun setWakeupInterval(context: Context, intervalSeconds: Int) = runBlocking {
    Log.i(TAG, "Setting Waker interval: $intervalSeconds")
    context.dataStore.edit { settings ->
        settings[intPreferencesKey(INTERVAL_SECONDS_KEY)] = intervalSeconds
    }
}