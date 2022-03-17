package com.solutionsmax.gurukoolmax_v3.remote.interactor

import com.solutionsmax.gurukoolmax_v3.core.data.LicenseEntity
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.remote.entity.FetchSiteCode
import com.solutionsmax.gurukoolmax_v3.remote.repository.RemoteTokenRepository
import javax.inject.Inject

class GetLicenseInfoBySiteCodeUseCase @Inject constructor(
    private val remoteTokenRepository: RemoteTokenRepository
) : UseCase<List<LicenseEntity>, FetchSiteCode>() {
    override suspend fun run(params: FetchSiteCode): Either<Failure, List<LicenseEntity>> =
        remoteTokenRepository.retrieveURLSBySiteCode(
            sAuthorization = params.sAuthorization,
            iStatusID = params.iStatusID,
            sSiteCode = params.SiteCode
        )

}