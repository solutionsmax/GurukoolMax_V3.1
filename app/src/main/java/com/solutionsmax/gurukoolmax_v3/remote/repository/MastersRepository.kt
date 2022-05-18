package com.solutionsmax.gurukoolmax_v3.remote.repository

import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_KNOWLEDGE_MANAGEMENT_CONTENT_TYPES
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_KNOWLEDGE_MANAGEMENT_FORMAT_TYPES
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_KNOWLEDGE_MANAGEMENT_SOURCES
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_SCHOOL_MAX_MASTER
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

    suspend fun populateBoard(
        url: String,
        sAuthorization: String,
        sTableName: String
    ): Either<Failure, MutableList<PopulateMasterListItem>> =
        Either.Right(
            masterApi.populateMasters(
                url = "$url$POPULATE_SCHOOL_MAX_MASTER",
                "${TokenConstants.BEARER} $sAuthorization",
                sTableName
            )
        )

    suspend fun populateKmContentType(
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

    suspend fun populateKmSubmissionCategory(
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

    suspend fun populateKmFormatTypes(
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

    suspend fun populateAcademicYear(
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

    suspend fun populateFocusAssignment(
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

    suspend fun populateApprovalStatus(
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