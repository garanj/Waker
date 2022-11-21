package com.garan.waker

import android.os.Bundle
import androidx.activity.ComponentActivity


const val TAG = "Waker"

/**
 * Activity for Waker UI.
 */
class WakerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runIfEnabledAndPermitted(
            context = this,
            label = "Setting next alarm"
        ) {
            setAlarm(it)
        }
        finish()
    }

}