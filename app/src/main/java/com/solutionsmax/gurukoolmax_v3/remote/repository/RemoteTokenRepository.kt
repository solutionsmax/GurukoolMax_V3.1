package com.solutionsmax.gurukoolmax_v3.remote.repository

import com.solutionsmax.gurukoolmax_v3.core.common.RequestBodyConstants
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.data.LicenseEntity
import com.solutionsmax.gurukoolmax_v3.core.data.token.TokenEntity
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.TokenApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject


class RemoteTokenRepository @Inject constructor(
    private val tokenApi: TokenApi
) {

    // Constructing Request Body for token generation
    private var body: RequestBody = TokenConstants.tokenValue
        .toRequestBody(RequestBodyConstants.REQUEST_BODY.toMediaTypeOrNull())

    // Generate Token
    suspend fun getRemoteTokenTest(): Either<Failure, TokenEntity> {
        return Either.Right(tokenApi.getToken(body))
    }

    // Fetch Site constants by REST URL
    suspend fun retrieveURLSBySiteCode(
        sAuthorization: String,
        iStatusID: Int,
        sSiteCode: String
    ): Either<Failure, List<LicenseEntity>> =
        Either.Right(
            tokenApi.retrieveURLSBySiteCode(
                "${TokenConstants.BEARER} $sAuthorization", iStatusID, sSiteCode
            )
        )
}
