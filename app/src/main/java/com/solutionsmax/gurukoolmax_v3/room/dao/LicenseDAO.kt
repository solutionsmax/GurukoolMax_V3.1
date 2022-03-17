package com.solutionsmax.gurukoolmax_v3.room.dao

import androidx.room.*
import com.solutionsmax.gurukoolmax_v3.room.entity.LicenseItem

@Dao
interface LicenseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun postLicenseInfo(tokenItem: LicenseItem): Long

    @Update
    suspend fun amendLicenseInfo(tokenItem: LicenseItem)

    @Delete
    suspend fun purgeLicenseInfo(tokenItem: LicenseItem)

    @Query("DELETE FROM license")
    suspend fun deleteAllLicense()

    @Query("SELECT * FROM license ORDER BY id DESC")
    suspend fun retrieveLicenseList(): List<LicenseItem>

}