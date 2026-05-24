package com.cameraspoof.domain.usecase

import com.cameraspoof.data.repository.AppRepository
import com.cameraspoof.domain.model.SpoofMode

class UpdateAppConfigUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(
        packageName: String,
        isEnabled: Boolean? = null,
        imagePath: String? = null,
        spoofMode: SpoofMode? = null
    ) {
        repository.updateAppConfig(packageName, isEnabled, imagePath, spoofMode)
    }
}
