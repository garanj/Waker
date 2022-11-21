package com.garan.waker

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log

fun hasNecessaryPermissions(context: Context) =
    context.checkSelfPermission(Manifest.permission.SYSTEM_ALERT_WINDOW) == PackageManager.PERMISSION_GRANTED

fun runIfEnabledAndPermitted(context: Context, label: String, block: (Context) -> Unit) {
    val enabled = isWakerEnabled(context)
    val granted = hasNecessaryPermissions(context)

    if (granted && enabled) {
        Log.i(TAG, "Waker is enabled; $label")
        block(context)
    } else if (enabled) {
        Log.w(TAG, "Waker is enabled but missing necessary permissions")
    } else {
        Log.i(TAG, "Waker is disabled")
    }
}