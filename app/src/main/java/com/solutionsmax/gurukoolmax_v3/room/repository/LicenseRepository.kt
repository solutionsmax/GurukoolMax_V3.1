package com.solutionsmax.gurukoolmax_v3.room.repository

import com.solutionsmax.gurukoolmax_v3.room.dao.LicenseDAO
import com.solutionsmax.gurukoolmax_v3.room.entity.LicenseItem
import javax.inject.Inject

class LicenseRepository @Inject constructor(
    private val licenseDAO: LicenseDAO
) {

    suspend fun insertLicense(licenseItem: LicenseItem): Long =
        licenseDAO.postLicenseInfo(licenseItem)

    suspend fun amendLicense(licenseItem: LicenseItem) =
        licenseDAO.amendLicenseInfo(licenseItem)

    suspend fun purgeLicense(licenseItem: LicenseItem) =
        licenseDAO.purgeLicenseInfo(licenseItem)

    suspend fun deleteAllLicense() = licenseDAO.deleteAllLicense()

    suspend fun retrieveLicense(): List<LicenseItem> =
        licenseDAO.retrieveLicenseList()
}