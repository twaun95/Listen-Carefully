package com.twaun95.listencarefully.common

import android.util.Log

object LCLogger {
    private const val TAG = "TAEWAUN"

    fun d(message: Any) { Log.d(TAG, "[Log]$message") }
}