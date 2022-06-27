package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.learning_session.PostLearningSessionItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.learning_session.RetrieveLearningSessionItem
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class LearningSessionRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {
    suspend fun postLearningSessionInfo(
        url: String,
        sAuthorization: String,
        postLearningSessionItem: PostLearningSessionItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postLearningSessionInfo(
                url = url + MethodConstants.POST_CURRICULUM_LESSON_PLANNER,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postLearningSessionItem = postLearningSessionItem
            )
        )

    suspend fun amendLearningSessionInfo(
        url: String,
        sAuthorization: String,
        postLearningSessionItem: PostLearningSessionItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendLearningSessionInfo(
                url = url + MethodConstants.AMEND_CURRICULUM_LESSON_PLANNER,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postLearningSessionItem = postLearningSessionItem
            )
        )

    suspend fun amendLearningSessionProgress(
        url: String,
        sAuthorization: String,
        sFacultyRemarks: String,
        iProgressStatusID: Int,
        dCompletionDate: String,
        iStatusID: Int,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendLearningSessionProgress(
                url = url + MethodConstants.AMEND_PROGRESS_INFO_CURRICULUM_LESSON_PLANNER,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                sFacultyRemarks, iProgressStatusID, dCompletionDate, iStatusID, id
            )
        )

    suspend fun setLearningSessionStatus(
        url: String,
        sAuthorization: String,
        iStatusID: Int,
        id:Int
    ):Either<Failure,Int> =
        Either.Right(
            academicsApi.setLearningSessionStatus(
                url = url + MethodConstants.SET_STATUS_CURRICULUM_LESSON_PLANNER,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iStatusID,id
            )
        )

    suspend fun checkDuplicateLearningSessionInfo(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassStandardID: Int,
        iYearID: Int,
        iSubjectID: Int,
        iSectionID: Int,
        iSessionTopicID: Int,
        sTopicDetails: String
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateLearningSessionInfo(
                url = url + MethodConstants.CHECK_DUPLICATE_CURRICULUM_LESSON_PLANNER,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassStandardID,
                iYearID,
                iSubjectID,
                iSectionID,
                iSessionTopicID,
                sTopicDetails
            )
        )

    suspend fun retrieveCurriculumTopicListByFaculty(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassStandardID: Int,
        iYearID: Int,
        iSubjectID: Int,
        iSectionID: Int,
        iFacultyID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveLearningSessionItem>> =
        Either.Right(
            academicsApi.retrieveCurriculumTopicListByFaculty(
                url = url + MethodConstants.RETRIEVE_TOPIC_LIST_BY_FACULTY_CURRICULUM_LESSON_PLANNER,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassStandardID,
                iYearID,
                iSubjectID,
                iSectionID,
                iFacultyID,
                iStatusID
            )
        )

    suspend fun retrieveLearningSessionDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<RetrieveLearningSessionItem>> =
        Either.Right(
            academicsApi.retrieveLearningSessionDetails(
                url = url + MethodConstants.RETRIEVE_DETAILS_CURRICULUM_LESSON_PLANNER,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveLearningSessionList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassStandardID: Int,
        iSubjectID: Int,
        iCurriculumID: Int,
        iFacultyID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveLearningSessionItem>> =
        Either.Right(
            academicsApi.retrieveLearningSessionList(
                url = url + MethodConstants.RETRIEVE_LIST_CURRICULUM_LESSON_PLANNER,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassStandardID,
                iSubjectID,
                iCurriculumID,
                iFacultyID,
                iStatusID
            )
        )

    suspend fun retrieveLearningSessionListByFaculty(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassStandardID: Int,
        iSubjectID: Int,
        iCurriculumID: Int,
        iFacultyID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveLearningSessionItem>> =
        Either.Right(
            academicsApi.retrieveLearningSessionListByFaculty(
                url = url + MethodConstants.RETRIEVE_LIST_BY_FACULTY_CURRICULUM_LESSON_PLANNER,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassStandardID,
                iSubjectID,
                iCurriculumID,
                iFacultyID,
                iStatusID
            )
        )

}