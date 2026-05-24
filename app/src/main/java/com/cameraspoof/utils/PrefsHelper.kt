package com.cameraspoof.utils

import android.content.Context
import android.content.SharedPreferences

object PrefsHelper {
    private const val PREFS_NAME = "app_configs"

    fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_WORLD_READABLE)
    }

    fun saveAppConfig(
        context: Context,
        packageName: String,
        isEnabled: Boolean,
        imagePath: String?,
        spoofMode: String
    ) {
        val prefs = getPrefs(context)
        prefs.edit().apply {
            putBoolean("${packageName}_enabled", isEnabled)
            if (imagePath != null) {
                putString("${packageName}_image", imagePath)
            } else {
                remove("${packageName}_image")
            }
            putString("${packageName}_mode", spoofMode)
            apply()
        }
    }

    fun getAppEnabled(context: Context, packageName: String): Boolean {
        return getPrefs(context).getBoolean("${packageName}_enabled", false)
    }

    fun getAppImagePath(context: Context, packageName: String): String? {
        return getPrefs(context).getString("${packageName}_image", null)
    }

    fun getAppSpoofMode(context: Context, packageName: String): String {
        return getPrefs(context).getString("${packageName}_mode", "ALL_CAMERAS") ?: "ALL_CAMERAS"
    }
}
