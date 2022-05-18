package com.solutionsmax.gurukoolmax_v3.room.dao

import androidx.room.*
import com.solutionsmax.gurukoolmax_v3.room.entity.SettingsItem

@Dao
interface SettingsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun postSettingsInfo(settingsItem: SettingsItem): Long

    @Update
    suspend fun amendSettingsInfo(settingsItem: SettingsItem)

    @Delete
    suspend fun purgeSettingsInfo(settingsItem: SettingsItem)

    @Query("DELETE FROM settings")
    suspend fun deleteAllSettings()

    @Query("SELECT * FROM settings ORDER BY id DESC")
    suspend fun retrieveSettingsList(): List<SettingsItem>
}