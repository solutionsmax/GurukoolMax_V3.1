package com.solutionsmax.gurukoolmax_v3.remote.interactor

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.remote.entity.TokenLicenseItem
import com.solutionsmax.gurukoolmax_v3.room.repository.LicenseRepository
import com.solutionsmax.gurukoolmax_v3.room.repository.TokenRepository
import javax.inject.Inject

class GetTokenLicenseUseCase @Inject constructor(
    private val licenseRepository: LicenseRepository,
    private val tokenRepository: TokenRepository
) : UseCase<TokenLicenseItem, Unit>() {
    override suspend fun run(params: Unit): Either<Failure, TokenLicenseItem> {
        val token = tokenRepository.retrieveToken().first().access_token
        val baseURl = licenseRepository.retrieveLicense().first().rest_url
        val assetURL = licenseRepository.retrieveLicense().first().asset_url
        val iGroupID = licenseRepository.retrieveLicense().first().group_id
        val iBranchID = licenseRepository.retrieveLicense().first().branch_id
        return Either.Right(
            TokenLicenseItem(
                sToken = token,
                sBaseURL = baseURl,
                sAssetURL = assetURL,
                iGroupID = iGroupID,
                iBranchID = iBranchID
            )
        )
    }
}