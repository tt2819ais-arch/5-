package com.cameraspoof

import android.app.Application
import com.cameraspoof.data.local.AppDatabase
import com.cameraspoof.data.repository.AppRepository
import com.cameraspoof.utils.FileUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.lsposed.hiddenapibypass.HiddenApiBypass

class CameraSpoofApp : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    lateinit var database: AppDatabase
        private set

    lateinit var repository: AppRepository
        private set

    override fun onCreate() {
        super.onCreate()

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            HiddenApiBypass.addHiddenApiExemptions("")
        }

        database = AppDatabase.getInstance(this)
        repository = AppRepository(
            context = this,
            appConfigDao = database.appConfigDao(),
            logDao = database.logDao()
        )

        applicationScope.launch {
            FileUtils.cleanupOldFiles(this@CameraSpoofApp)
        }
    }
}
