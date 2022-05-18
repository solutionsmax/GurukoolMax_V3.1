package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic.PostCceScholasticItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic.RetrieveCceScholasticItem
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.AMEND_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_ALL_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_GRADE_POINT_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_GRADE_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_MAX_SCORE_RANGE_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_MIN_SCORE_RANGE_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POST_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.REMOVE_GRADE_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_DETAILS_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_LIST_BY_GRADE_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_LIST_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.SET_STATUS_SCHOLASTIC_GRADING
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class CceScholasticRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {

    suspend fun postScholasticGrading(
        url: String,
        sAuthorization: String,
        postCceScholasticItem: PostCceScholasticItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postScholasticGrading(
                url = url + POST_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postCceScholasticItem = postCceScholasticItem
            )
        )

    suspend fun amendScholasticGrading(
        url: String,
        sAuthorization: String,
        postCceScholasticItem: PostCceScholasticItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendScholasticGrading(
                url = url + AMEND_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postCceScholasticItem = postCceScholasticItem
            )
        )

    suspend fun checkDuplicateScholasticGradingAll(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iMinScore: Int,
        iMaxScore: Int,
        sGrade: String,
        dGradePoint: Double
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateScholasticGradingAll(
                url = url + CHECK_DUPLICATE_ALL_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iClassID, iMinScore, iMaxScore, sGrade, dGradePoint
            )
        )

    suspend fun checkDuplicateScholasticGrading(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        sGrade: String
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateScholasticGrading(
                url = url + CHECK_DUPLICATE_GRADE_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iClassID, sGrade
            )
        )

    suspend fun checkDuplicateScholasticGradingByMaxRange(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iRangeValue: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateScholasticGradingByMaxRange(
                url = url + CHECK_DUPLICATE_MAX_SCORE_RANGE_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iClassID, iRangeValue
            )
        )

    suspend fun checkDuplicateScholasticGradingByMinRange(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iRangeValue: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateScholasticGradingByMinRange(
                url = url + CHECK_DUPLICATE_MIN_SCORE_RANGE_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iClassID, iRangeValue
            )
        )

    suspend fun checkDuplicateScholasticGradingPoint(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        dGradePoint: Double
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateScholasticGradingPoint(
                url = url + CHECK_DUPLICATE_GRADE_POINT_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iClassID, dGradePoint
            )
        )

    suspend fun removeScholasticGradingGrade(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.removeScholasticGradingGrade(
                url = url + REMOVE_GRADE_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun setStatusScholasticGrading(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.setStatusScholasticGrading(
                url = url + SET_STATUS_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveDetailsScholasticGrading(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<RetrieveCceScholasticItem>> =
        Either.Right(
            academicsApi.retrieveDetailsScholasticGrading(
                url = url + RETRIEVE_DETAILS_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveListScholasticGrading(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveCceScholasticItem>> =
        Either.Right(
            academicsApi.retrieveListScholasticGrading(
                url = url + RETRIEVE_LIST_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iClassID, iStatusID
            )
        )

    suspend fun retrieveGradeByListScholasticGrading(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iMarks: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveCceScholasticItem>> =
        Either.Right(
            academicsApi.retrieveGradeByListScholasticGrading(
                url = url + RETRIEVE_LIST_BY_GRADE_SCHOLASTIC_GRADING,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iClassID, iMarks, iStatusID
            )
        )
}