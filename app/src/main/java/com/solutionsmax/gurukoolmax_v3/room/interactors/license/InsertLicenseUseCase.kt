package com.solutionsmax.gurukoolmax_v3.room.interactors.license

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.room.entity.LicenseItem
import com.solutionsmax.gurukoolmax_v3.room.repository.LicenseRepository
import javax.inject.Inject

class InsertLicenseUseCase @Inject constructor(
    private val licenseRepository: LicenseRepository
) : UseCase<Long, LicenseItem>() {
    override suspend fun run(params: LicenseItem): Either<Failure, Long> {
        return Either.Right(licenseRepository.insertLicense(params))
    }
}