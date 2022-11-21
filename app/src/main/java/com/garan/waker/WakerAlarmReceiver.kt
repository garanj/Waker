package com.garan.waker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class WakerAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) {
            return
        }

        runIfEnabledAndPermitted(
            context = context,
            label = "Launching activity"
        ) {
            launchActivity(it)
        }
    }
}