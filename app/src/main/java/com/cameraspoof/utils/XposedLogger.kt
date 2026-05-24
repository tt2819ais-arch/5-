package com.cameraspoof.utils

import android.util.Log

object XposedLogger {
    private const val TAG = "CameraSpoof"

    fun log(message: String) {
        Log.i(TAG, message)
    }

    fun logDebug(message: String) {
        Log.d(TAG, message)
    }

    fun logError(message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Log.e(TAG, message, throwable)
        } else {
            Log.e(TAG, message)
        }
    }

    fun logWarning(message: String) {
        Log.w(TAG, message)
    }
}
