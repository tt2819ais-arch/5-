package com.cameraspoof.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppConfigDao {
    @Query("SELECT * FROM app_configs")
    fun getAllConfigs(): Flow<List<AppConfigEntity>>

    @Query("SELECT * FROM app_configs WHERE packageName = :packageName")
    suspend fun getConfig(packageName: String): AppConfigEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfig(config: AppConfigEntity)

    @Query("DELETE FROM app_configs WHERE packageName = :packageName")
    suspend fun deleteConfig(packageName: String)

    @Query("UPDATE app_configs SET isEnabled = :isEnabled WHERE packageName = :packageName")
    suspend fun updateEnabled(packageName: String, isEnabled: Boolean)

    @Query("UPDATE app_configs SET selectedImagePath = :imagePath WHERE packageName = :packageName")
    suspend fun updateImagePath(packageName: String, imagePath: String?)

    @Query("UPDATE app_configs SET spoofMode = :mode WHERE packageName = :packageName")
    suspend fun updateSpoofMode(packageName: String, mode: String)
}
