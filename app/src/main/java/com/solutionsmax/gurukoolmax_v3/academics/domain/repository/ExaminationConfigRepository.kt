package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup.PostExamSetupInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup.RetrieveExamSetupDetails
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class ExaminationConfigRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {

    suspend fun postExaminationConfig(
        url: String,
        sAuthorization: String,
        postExamSetupInfoItem: PostExamSetupInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postExaminationConfig(
                url = "$url${MethodConstants.POST_EXAM_SETUP}",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postExamSetupInfoItem = postExamSetupInfoItem
            )
        )

    suspend fun amendExaminationConfig(
        url: String,
        sAuthorization: String,
        postExamSetupInfoItem: PostExamSetupInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendExaminationConfig(
                url = "$url${MethodConstants.AMEND_EXAM_SETUP}",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postExamSetupInfoItem = postExamSetupInfoItem
            )
        )

    suspend fun checkDuplicateExaminationConfig(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iYearID: Int,
        iScheduledNameID: Int,
        iSubjectID: Int,
        tStartTime: String,
        tEndTime: String,
        dExamDate: String,
        iStatusID: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateExaminationConfig(
                url = "$url${MethodConstants.CHECK_DUPLICATE_EXAM_SETUP}",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassID,
                iYearID,
                iScheduledNameID,
                iSubjectID,
                tStartTime,
                tEndTime,
                dExamDate,
                iStatusID
            )
        )

    suspend fun retrieveDetailsExaminationConfig(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<RetrieveExamSetupDetails>> =
        Either.Right(
            academicsApi.retrieveDetailsExaminationConfig(
                url = "$url${MethodConstants.RETRIEVE_DETAILS_EXAM_SETUP}",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveListExaminationConfig(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iYearID: Int,
        iScheduledID: Int,
        iSubjectID: Int,
        iAssessmentID: Int,
        iStatusID: Int,
        iStudentID: Int
    ): Either<Failure, List<RetrieveExamSetupDetails>> =
        Either.Right(
            academicsApi.retrieveListExaminationConfig(
                url = "$url${MethodConstants.RETRIEVE_LIST_EXAM_SETUP}",
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassID,
                iYearID,
                iScheduledID,
                iSubjectID,
                iAssessmentID,
                iStatusID,
                iStudentID
            )
        )
}