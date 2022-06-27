package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_result.PostExamResultItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_result.RetrieveExamResultsItems
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class ExamResultRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {

    suspend fun postExamResult(
        url: String,
        sAuthorization: String,
        postExamResultItem: PostExamResultItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postExamResult(
                url = "$url${MethodConstants.POST_EXAM_RESULT}",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postExamResultItem = postExamResultItem
            )
        )

    suspend fun amendExamResult(
        url: String,
        sAuthorization: String,
        postExamResultItem: PostExamResultItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendExamResult(
                url = "$url${MethodConstants.AMEND_EXAM_RESULT}",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postExamResultItem = postExamResultItem
            )
        )

    suspend fun checkDuplicateExamResult(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iYearID: Int,
        iScheduledID: Int,
        iSubjectID: Int,
        iStudentID: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateExamResult(
                url = "$url${MethodConstants.CHECK_DUPLICATE_EXAM_RESULT}",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassID,
                iYearID,
                iScheduledID,
                iSubjectID,
                iStudentID
            )
        )

    suspend fun retrieveExamResultDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<RetrieveExamResultsItems>> =
        Either.Right(
            academicsApi.retrieveExamResultDetails(
                url = "$url${MethodConstants.RETRIEVE_DETAILS_EXAM_RESULT}",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveExamResultList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iYearID: Int,
        iSubjectID: Int,
        iStudentID: Int,
        iScheduledID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveExamResultsItems>> =
        Either.Right(
            academicsApi.retrieveExamResultList(
                url = "$url${MethodConstants.RETRIEVE_LIST_EXAM_RESULT}",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassID,
                iYearID,
                iSubjectID,
                iStudentID,
                iScheduledID,
                iStatusID
            )
        )
}