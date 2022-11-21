package com.garan.waker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

const val INTERVAL_SECONDS_KEY = "interval_seconds"
const val ENABLED_KEY = "enabled"

const val SET_WAKEUP_INTERVAL_ACTION = "com.garan.waker.SET_WAKEUP_INTERVAL"
const val SET_WAKEUP_ON_ACTION = "com.garan.waker.SET_WAKEUP_ON"
const val SET_WAKEUP_OFF_ACTION = "com.garan.waker.SET_WAKEUP_OFF"
const val DEFAULT_INTERVAL_SECONDS = 300

class WakerSettingsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) {
            return
        }
        intent?.let {
            when (it.action) {
                SET_WAKEUP_INTERVAL_ACTION -> {
                    val intervalSeconds =
                        it.getIntExtra(INTERVAL_SECONDS_KEY, DEFAULT_INTERVAL_SECONDS)
                    setWakeupInterval(context, intervalSeconds)

                    // As the interval has been changed, remove the old alarm and set a new one
                    deleteLaunchAlarm(context)
                    setAlarm(context)
                }

                SET_WAKEUP_OFF_ACTION,
                SET_WAKEUP_ON_ACTION -> {
                    val shouldBeEnabled = it.action == SET_WAKEUP_ON_ACTION
                    setWakerEnabled(context, shouldBeEnabled)
                }

                else -> {
                    Log.w(TAG, "Unknown action: ${it.action}")
                }
            }

            if (it.action == SET_WAKEUP_ON_ACTION) {
                runIfEnabledAndPermitted(
                    context = context,
                    label = "Launching activity"
                ) { runContext ->
                    launchActivity(runContext)
                }
            } else if (it.action == SET_WAKEUP_OFF_ACTION) {
                deleteLaunchAlarm(context)
            }
        }
    }
}