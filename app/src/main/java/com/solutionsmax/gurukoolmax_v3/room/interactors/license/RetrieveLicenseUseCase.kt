package com.solutionsmax.gurukoolmax_v3.room.interactors.license

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.room.entity.LicenseItem
import com.solutionsmax.gurukoolmax_v3.room.repository.LicenseRepository
import javax.inject.Inject

class RetrieveLicenseUseCase @Inject constructor(
    private val licenseRepository: LicenseRepository
) : UseCase<List<LicenseItem>, Unit>() {
    override suspend fun run(params: Unit): Either<Failure, List<LicenseItem>> {
        return Either.Right(licenseRepository.retrieveLicense())
    }
}