package com.cameraspoof.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

object FileUtils {
    suspend fun exportLogsToFile(context: Context, content: String): Boolean =
        withContext(Dispatchers.IO) {
            try {
                val logsDir = File(context.filesDir, "logs")
                if (!logsDir.exists()) {
                    logsDir.mkdirs()
                }

                val fileName = "cameraspoof_logs_${System.currentTimeMillis()}.txt"
                val file = File(logsDir, fileName)

                file.writeText(content)

                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    file
                )

                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_STREAM, uri)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }

                context.startActivity(Intent.createChooser(shareIntent, "Export Logs").apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })

                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

    fun cleanupOldFiles(context: Context, maxAgeMillis: Long = 30L * 24 * 60 * 60 * 1000) {
        try {
            val imagesDir = File(context.filesDir, "spoof_images")
            if (imagesDir.exists()) {
                val now = System.currentTimeMillis()
                imagesDir.listFiles()?.forEach { file ->
                    if (now - file.lastModified() > maxAgeMillis) {
                        file.delete()
                    }
                }
            }

            val logsDir = File(context.filesDir, "logs")
            if (logsDir.exists()) {
                val now = System.currentTimeMillis()
                logsDir.listFiles()?.forEach { file ->
                    if (now - file.lastModified() > maxAgeMillis) {
                        file.delete()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
