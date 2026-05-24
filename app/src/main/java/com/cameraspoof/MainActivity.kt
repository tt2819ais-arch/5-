package com.cameraspoof

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cameraspoof.domain.model.SpoofMode
import com.cameraspoof.ui.screens.LogsScreen
import com.cameraspoof.ui.screens.MainScreen
import com.cameraspoof.ui.theme.CameraSpoofTheme
import com.cameraspoof.utils.FileUtils
import com.cameraspoof.utils.ImageUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val app by lazy { application as CameraSpoofApp }
    private val repository by lazy { app.repository }

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _apps = MutableStateFlow(emptyList<com.cameraspoof.domain.model.AppInfo>())
    private val apps: StateFlow<List<com.cameraspoof.domain.model.AppInfo>> = _apps.asStateFlow()

    private val _logs = MutableStateFlow(emptyList<com.cameraspoof.domain.model.LogEntry>())
    private val logs: StateFlow<List<com.cameraspoof.domain.model.LogEntry>> = _logs.asStateFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repository.getInstalledApps().collect { appList ->
                val query = _searchQuery.value
                _apps.value = if (query.isEmpty()) {
                    appList
                } else {
                    appList.filter {
                        it.appName.contains(query, ignoreCase = true) ||
                        it.packageName.contains(query, ignoreCase = true)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repository.getLogs().collect { logList ->
                _logs.value = logList
            }
        }

        lifecycleScope.launch {
            _searchQuery.collect { query ->
                val allApps = _apps.value
                _apps.value = if (query.isEmpty()) {
                    allApps
                } else {
                    allApps.filter {
                        it.appName.contains(query, ignoreCase = true) ||
                        it.packageName.contains(query, ignoreCase = true)
                    }
                }
            }
        }

        setContent {
            CameraSpoofTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable("main") {
                            MainScreen(
                                appsState = apps,
                                searchQuery = searchQuery,
                                onSearchQueryChange = { _searchQuery.value = it },
                                onAppToggle = { packageName, enabled ->
                                    handleAppToggle(packageName, enabled)
                                },
                                onImageSelected = { packageName, uri ->
                                    handleImageSelected(packageName, uri)
                                },
                                onImageRemoved = { packageName ->
                                    handleImageRemoved(packageName)
                                },
                                onSpoofModeChanged = { packageName, mode ->
                                    handleSpoofModeChanged(packageName, mode)
                                },
                                onNavigateToLogs = {
                                    navController.navigate("logs")
                                }
                            )
                        }

                        composable("logs") {
                            LogsScreen(
                                logsState = logs,
                                onClearLogs = {
                                    handleClearLogs()
                                },
                                onExportLogs = {
                                    handleExportLogs()
                                },
                                onNavigateBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleAppToggle(packageName: String, enabled: Boolean) {
        lifecycleScope.launch {
            try {
                repository.updateAppConfig(
                    packageName = packageName,
                    isEnabled = enabled
                )

                val prefs = getSharedPreferences("app_configs", MODE_WORLD_READABLE)
                prefs.edit().apply {
                    putBoolean("${packageName}_enabled", enabled)
                    apply()
                }

                Toast.makeText(
                    this@MainActivity,
                    if (enabled) "Enabled for $packageName" else "Disabled for $packageName",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.error_saving_config),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun handleImageSelected(packageName: String, uri: Uri) {
        lifecycleScope.launch {
            try {
                val imagePath = ImageUtils.copyImageToInternalStorage(
                    context = this@MainActivity,
                    uri = uri,
                    packageName = packageName
                )

                if (imagePath != null) {
                    repository.updateAppConfig(
                        packageName = packageName,
                        imagePath = imagePath
                    )

                    val prefs = getSharedPreferences("app_configs", MODE_WORLD_READABLE)
                    prefs.edit().apply {
                        putString("${packageName}_image", imagePath)
                        apply()
                    }

                    Toast.makeText(
                        this@MainActivity,
                        "Image selected successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.error_selecting_image),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.error_selecting_image),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun handleImageRemoved(packageName: String) {
        lifecycleScope.launch {
            try {
                val config = repository.getAppConfig(packageName)
                config?.selectedImagePath?.let { path ->
                    ImageUtils.deleteImage(path)
                }

                repository.updateAppConfig(
                    packageName = packageName,
                    imagePath = null
                )

                val prefs = getSharedPreferences("app_configs", MODE_WORLD_READABLE)
                prefs.edit().apply {
                    remove("${packageName}_image")
                    apply()
                }

                Toast.makeText(
                    this@MainActivity,
                    "Image removed",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun handleSpoofModeChanged(packageName: String, mode: SpoofMode) {
        lifecycleScope.launch {
            try {
                repository.updateAppConfig(
                    packageName = packageName,
                    spoofMode = mode
                )

                val prefs = getSharedPreferences("app_configs", MODE_WORLD_READABLE)
                prefs.edit().apply {
                    putString("${packageName}_mode", mode.name)
                    apply()
                }

                Toast.makeText(
                    this@MainActivity,
                    "Spoof mode updated",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun handleClearLogs() {
        lifecycleScope.launch {
            try {
                repository.clearLogs()
                Toast.makeText(
                    this@MainActivity,
                    "Logs cleared",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun handleExportLogs() {
        lifecycleScope.launch {
            try {
                val logsContent = repository.exportLogs()
                val success = FileUtils.exportLogsToFile(
                    context = this@MainActivity,
                    content = logsContent
                )

                if (success) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.logs_exported),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.logs_export_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.logs_export_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
