package com.garan.waker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent

fun getLaunchPendingIntent(context: Context): PendingIntent {
    val intent = Intent(context, WakerAlarmReceiver::class.java)
    return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
}

fun launchActivity(context: Context) {
    context.startActivity(getLaunchIntent(context))
}

fun getLaunchIntent(context: Context) = Intent(context, WakerActivity::class.java).apply {
    flags = Intent.FLAG_ACTIVITY_NEW_TASK
}