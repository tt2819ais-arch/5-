package com.cameraspoof.xposed

import android.content.Context
import android.content.SharedPreferences
import de.robv.android.xposed.XSharedPreferences

object XposedPrefs {
    private const val PACKAGE_NAME = "com.cameraspoof"
    private const val PREFS_NAME = "app_configs"

    fun getPrefs(): XSharedPreferences {
        return XSharedPreferences(PACKAGE_NAME, PREFS_NAME).apply {
            makeWorldReadable()
        }
    }

    fun isAppEnabled(prefs: XSharedPreferences, packageName: String): Boolean {
        prefs.reload()
        return prefs.getBoolean("${packageName}_enabled", false)
    }

    fun getImagePath(prefs: XSharedPreferences, packageName: String): String? {
        prefs.reload()
        return prefs.getString("${packageName}_image", null)
    }

    fun getSpoofMode(prefs: XSharedPreferences, packageName: String): String {
        prefs.reload()
        return prefs.getString("${packageName}_mode", "ALL_CAMERAS") ?: "ALL_CAMERAS"
    }
}
