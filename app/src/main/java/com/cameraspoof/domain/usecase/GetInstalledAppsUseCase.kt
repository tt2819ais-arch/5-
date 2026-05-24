package com.cameraspoof.domain.usecase

import com.cameraspoof.data.repository.AppRepository
import com.cameraspoof.domain.model.AppInfo
import kotlinx.coroutines.flow.Flow

class GetInstalledAppsUseCase(private val repository: AppRepository) {
    operator fun invoke(): Flow<List<AppInfo>> {
        return repository.getInstalledApps()
    }
}
