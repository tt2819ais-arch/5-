package com.cameraspoof.domain.model

data class AppInfo(
    val packageName: String,
    val appName: String,
    val icon: android.graphics.drawable.Drawable?,
    val isEnabled: Boolean = false,
    val selectedImageUri: String? = null,
    val spoofMode: SpoofMode = SpoofMode.ALL_CAMERAS
)

enum class SpoofMode {
    FRONT_CAMERA,
    BACK_CAMERA,
    ALL_CAMERAS
}
