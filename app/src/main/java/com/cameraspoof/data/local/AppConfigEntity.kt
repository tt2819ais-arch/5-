package com.cameraspoof.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_configs")
data class AppConfigEntity(
    @PrimaryKey
    val packageName: String,
    val isEnabled: Boolean,
    val selectedImagePath: String?,
    val spoofMode: String
)
