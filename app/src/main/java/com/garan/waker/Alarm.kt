package com.garan.waker

import android.app.AlarmManager
import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity

fun setAlarm(context: Context) {
    val pendingIntent = getLaunchPendingIntent(context)
    val interval = getWakeupInterval(context)
    Log.i(TAG, "Setting Waker alarm for $interval seconds")
    val alarmManager = context.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager
    val now = System.currentTimeMillis()
    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        now + getWakeupInterval(context) * 1000,
        pendingIntent
    )
}

fun deleteLaunchAlarm(context: Context) {
    Log.i(TAG, "Deleting waker alarm")
    val alarmManager = context.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(getLaunchPendingIntent(context))
}