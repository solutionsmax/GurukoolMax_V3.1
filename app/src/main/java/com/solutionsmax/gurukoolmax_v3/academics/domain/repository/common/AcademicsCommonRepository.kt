package com.solutionsmax.gurukoolmax_v3.academics.domain.repository.common

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class AcademicsCommonRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {

    suspend fun populateClassSemester(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iStatusID: Int
    ): Either<Failure, MutableList<PopulateClassItems>> =
        Either.Right(
            academicsApi.populateClassSemester(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iStatusID
            )
        )

    suspend fun populateClass(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iYearID: Int,
        iStatusID: Int
    ): Either<Failure, MutableList<PopulateClassItems>> =
        Either.Right(
            academicsApi.populateClass(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iYearID, iStatusID
            )
        )

}