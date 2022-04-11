package com.solutionsmax.gurukoolmax_v3.remote.repository

import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_TIMETABLE_TIME_CYCLE
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_TIMETABLE_TIME_RANGE_HR
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_TIMETABLE_TIME_RANGE_MM
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_MASTER_LIST
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

    suspend fun populateHH(
        url: String,
        sAuthorization: String,
        sTableName: String
    ): Either<Failure, MutableList<PopulateMasterListItem>> =
        Either.Right(
            masterApi.populateMasters(
                url = url,
                "${TokenConstants.BEARER} $sAuthorization",
                sTableName
            )
        )

    suspend fun populateMM(
        url: String,
        sAuthorization: String,
        sTableName: String
    ): Either<Failure, MutableList<PopulateMasterListItem>> =
        Either.Right(
            masterApi.populateMasters(
                url = url,
                "${TokenConstants.BEARER} $sAuthorization",
                sTableName
            )
        )

    suspend fun populateTimeCycle(
        url: String,
        sAuthorization: String,
        sTableName: String
    ): Either<Failure, MutableList<PopulateMasterListItem>> =
        Either.Right(
            masterApi.populateMasters(
                url = url,
                "${TokenConstants.BEARER} $sAuthorization",
                sTableName
            )
        )

}