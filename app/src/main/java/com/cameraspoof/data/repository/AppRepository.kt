package com.cameraspoof.data.repository

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.cameraspoof.data.local.AppConfigDao
import com.cameraspoof.data.local.AppConfigEntity
import com.cameraspoof.data.local.LogDao
import com.cameraspoof.data.local.LogEntity
import com.cameraspoof.domain.model.AppInfo
import com.cameraspoof.domain.model.LogEntry
import com.cameraspoof.domain.model.LogLevel
import com.cameraspoof.domain.model.SpoofMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class AppRepository(
    private val context: Context,
    private val appConfigDao: AppConfigDao,
    private val logDao: LogDao
) {
    private val packageManager: PackageManager = context.packageManager

    fun getInstalledApps(): Flow<List<AppInfo>> = flow {
        val installedApps = withContext(Dispatchers.IO) {
            packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
                .filter { it.flags and ApplicationInfo.FLAG_SYSTEM == 0 }
                .sortedBy { packageManager.getApplicationLabel(it).toString().lowercase() }
        }

        appConfigDao.getAllConfigs().collect { configs ->
            val configMap = configs.associateBy { it.packageName }

            val appInfoList = installedApps.map { appInfo ->
                val config = configMap[appInfo.packageName]
                AppInfo(
                    packageName = appInfo.packageName,
                    appName = packageManager.getApplicationLabel(appInfo).toString(),
                    icon = try {
                        packageManager.getApplicationIcon(appInfo.packageName)
                    } catch (e: Exception) {
                        null
                    },
                    isEnabled = config?.isEnabled ?: false,
                    selectedImageUri = config?.selectedImagePath,
                    spoofMode = config?.spoofMode?.let {
                        SpoofMode.valueOf(it)
                    } ?: SpoofMode.ALL_CAMERAS
                )
            }

            emit(appInfoList)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun updateAppConfig(
        packageName: String,
        isEnabled: Boolean? = null,
        imagePath: String? = null,
        spoofMode: SpoofMode? = null
    ) = withContext(Dispatchers.IO) {
        val existingConfig = appConfigDao.getConfig(packageName)

        if (existingConfig != null) {
            if (isEnabled != null) {
                appConfigDao.updateEnabled(packageName, isEnabled)
            }
            if (imagePath != null) {
                appConfigDao.updateImagePath(packageName, imagePath)
            }
            if (spoofMode != null) {
                appConfigDao.updateSpoofMode(packageName, spoofMode.name)
            }
        } else {
            appConfigDao.insertConfig(
                AppConfigEntity(
                    packageName = packageName,
                    isEnabled = isEnabled ?: false,
                    selectedImagePath = imagePath,
                    spoofMode = spoofMode?.name ?: SpoofMode.ALL_CAMERAS.name
                )
            )
        }
    }

    suspend fun getAppConfig(packageName: String): AppConfigEntity? =
        withContext(Dispatchers.IO) {
            appConfigDao.getConfig(packageName)
        }

    suspend fun deleteAppConfig(packageName: String) = withContext(Dispatchers.IO) {
        appConfigDao.deleteConfig(packageName)
    }

    fun getLogs(): Flow<List<LogEntry>> = flow {
        logDao.getAllLogs().collect { entities ->
            val logs = entities.map { entity ->
                LogEntry(
                    id = entity.id,
                    timestamp = entity.timestamp,
                    packageName = entity.packageName,
                    message = entity.message,
                    level = LogLevel.valueOf(entity.level)
                )
            }
            emit(logs)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun addLog(log: LogEntry) = withContext(Dispatchers.IO) {
        logDao.insertLog(
            LogEntity(
                timestamp = log.timestamp,
                packageName = log.packageName,
                message = log.message,
                level = log.level.name
            )
        )

        val count = logDao.getLogCount()
        if (count > 5000) {
            val weekAgo = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)
            logDao.deleteOldLogs(weekAgo)
        }
    }

    suspend fun clearLogs() = withContext(Dispatchers.IO) {
        logDao.clearLogs()
    }

    suspend fun exportLogs(): String = withContext(Dispatchers.IO) {
        val logs = mutableListOf<LogEntity>()
        logDao.getAllLogs().collect { logs.addAll(it) }

        buildString {
            appendLine("CameraSpoof Logs Export")
            appendLine("Generated: ${System.currentTimeMillis()}")
            appendLine("=" .repeat(80))
            appendLine()

            logs.forEach { log ->
                appendLine("[${log.level}] ${log.timestamp}")
                appendLine("Package: ${log.packageName}")
                appendLine("Message: ${log.message}")
                appendLine("-".repeat(80))
            }
        }
    }
}
