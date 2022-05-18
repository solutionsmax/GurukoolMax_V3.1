package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PopulateExamScheduleListItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PostExamScheduleItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.RetrieveExamScheduleItems
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.AMEND_EXAM_SCHEDULE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_EXAM_SCHEDULE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_LIST_EXAM_SCHEDULE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POST_EXAM_SCHEDULE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_DETAILS_EXAM_SCHEDULE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_LIST_EXAM_SCHEDULE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.SET_STATUS_EXAM_SCHEDULE
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class ExamScheduleRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {

    suspend fun postExamScheduleInfo(
        url: String,
        sAuthorization: String,
        postExamScheduleItem: PostExamScheduleItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postExamScheduleInfo(
                url = "$url$POST_EXAM_SCHEDULE",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postExamScheduleItem = postExamScheduleItem
            )
        )

    suspend fun amendExamScheduleInfo(
        url: String,
        sAuthorization: String,
        postExamScheduleItem: PostExamScheduleItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendExamScheduleInfo(
                url = "$url$AMEND_EXAM_SCHEDULE",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postExamScheduleItem = postExamScheduleItem
            )
        )

    suspend fun setStatusExamScheduleInfo(
        url: String,
        sAuthorization: String,
        iStatusID: Int,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.setStatusExamScheduleInfo(
                url = "$url$SET_STATUS_EXAM_SCHEDULE",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iStatusID, id
            )
        )

    suspend fun checkDuplicateExamScheduleInfo(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iYearID: Int,
        sScheduleName: String
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateExamScheduleInfo(
                url = "$url$CHECK_DUPLICATE_EXAM_SCHEDULE",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iYearID, sScheduleName
            )
        )

    suspend fun populateExamScheduleList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iYearID: Int,
        iStatusID: Int
    ): Either<Failure, List<PopulateExamScheduleListItem>> =
        Either.Right(
            academicsApi.populateExamScheduleList(
                url = "$url$POPULATE_LIST_EXAM_SCHEDULE",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iYearID, iStatusID
            )
        )

    suspend fun retrieveExamScheduleDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<RetrieveExamScheduleItems>> =
        Either.Right(
            academicsApi.retrieveExamScheduleDetails(
                url = "$url$RETRIEVE_DETAILS_EXAM_SCHEDULE",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveExamScheduleList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveExamScheduleItems>> =
        Either.Right(
            academicsApi.retrieveExamScheduleList(
                url = "$url$RETRIEVE_LIST_EXAM_SCHEDULE",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iStatusID
            )
        )
}