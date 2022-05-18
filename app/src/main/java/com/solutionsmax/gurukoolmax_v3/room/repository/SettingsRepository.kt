package com.solutionsmax.gurukoolmax_v3.room.repository

import com.solutionsmax.gurukoolmax_v3.room.dao.SettingsDAO
import com.solutionsmax.gurukoolmax_v3.room.entity.SettingsItem
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val settingsDAO: SettingsDAO
) {
    suspend fun insertSettings(settingsItem: SettingsItem): Long =
        settingsDAO.postSettingsInfo(settingsItem)

    suspend fun amendSettings(settingsItem: SettingsItem) =
        settingsDAO.amendSettingsInfo(settingsItem)

    suspend fun purgeSettings(settingsItem: SettingsItem) =
        settingsDAO.purgeSettingsInfo(settingsItem)

    suspend fun deleteAllSettings() = settingsDAO.deleteAllSettings()

    suspend fun retrieveSettings(): List<SettingsItem> =
        settingsDAO.retrieveSettingsList()
}