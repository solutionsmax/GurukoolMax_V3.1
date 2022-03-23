package com.solutionsmax.gurukoolmax_v3.remote.repository

import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.MasterApi
import javax.inject.Inject

class MastersRepository @Inject constructor(
    private val masterApi: MasterApi
) {

    suspend fun populateMasters(
        url: String,
        sAuthorization: String, sTableName: String
    ): Either<Failure, MutableList<PopulateMasterListItem>> =
        Either.Right(
            masterApi.populateMasters(
                url, "${TokenConstants.BEARER} $sAuthorization", sTableName
            )
        )

    suspend fun populateMake(
        url: String,
        sAuthorization: String, sTableName: String
    ): Either<Failure, MutableList<PopulateMasterListItem>> =
        Either.Right(
            masterApi.populateMasters(
                url, "${TokenConstants.BEARER} $sAuthorization", sTableName
            )
        )

    suspend fun populateManufactureYear(
        url: String,
        sAuthorization: String, sTableName: String
    ): Either<Failure, MutableList<PopulateMasterListItem>> =
        Either.Right(
            masterApi.populateMasters(
                url, "${TokenConstants.BEARER} $sAuthorization", sTableName
            )
        )

    suspend fun populateFuelType(
        url: String,
        sAuthorization: String, sTableName: String
    ): Either<Failure, MutableList<PopulateMasterListItem>> =
        Either.Right(
            masterApi.populateMasters(
                url, "${TokenConstants.BEARER} $sAuthorization", sTableName
            )
        )

}