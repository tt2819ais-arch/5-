package com.cameraspoof.utils

object Constants {
    const val PREFS_NAME = "app_configs"
    const val DATABASE_NAME = "camera_spoof_db"

    // SharedPreferences keys
    const val KEY_ENABLED_SUFFIX = "_enabled"
    const val KEY_IMAGE_SUFFIX = "_image"
    const val KEY_MODE_SUFFIX = "_mode"

    // Default values
    const val DEFAULT_SPOOF_MODE = "ALL_CAMERAS"

    // File paths
    const val SPOOF_IMAGES_DIR = "spoof_images"
    const val LOGS_DIR = "logs"

    // Limits
    const val MAX_LOGS = 5000
    const val LOG_RETENTION_DAYS = 7
    const val FILE_RETENTION_DAYS = 30

    // Image processing
    const val MAX_IMAGE_WIDTH = 1920
    const val MAX_IMAGE_HEIGHT = 1080
    const val IMAGE_QUALITY = 90
}
