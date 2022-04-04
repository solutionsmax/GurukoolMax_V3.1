package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.ManagementApi
import javax.inject.Inject

class OperationsRepository @Inject constructor(
    private val managementApi: ManagementApi
) {

    suspend fun validateOperationsLogin(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        sUsername: String,
        sPassword: String
    ): Either<Failure, Int> =
        Either.Right(
            managementApi.validateOperationsLogin(
                url,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                sUsername,
                sPassword
            )
        )
}