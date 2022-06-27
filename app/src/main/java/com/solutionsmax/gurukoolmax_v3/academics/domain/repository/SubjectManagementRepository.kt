package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PostSubjectManagementInfo
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.RetrieveSubjectInfoItems
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.AMEND_SUBJECT_MANAGEMENT_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_SUBJECT_MANAGEMENT_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_SUBJECT_LIST_SUBJECT_MANAGEMENT_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_SUBJECT_PROGRAM_LIST
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POST_SUBJECT_MANAGEMENT_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_SUBJECT_LIST_MAP_MAP_DETAILS_SUBJECT_MANAGEMENT_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_SUBJECT_LIST_SUBJECT_MANAGEMENT_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.SET_ACTIVATION_STATUS_SUBJECT_MANAGEMENT_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class SubjectManagementRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {

    suspend fun postSubjectManagementInfo(
        url: String,
        sAuthorization: String,
        postSubjectManagementInfo: PostSubjectManagementInfo
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postSubjectManagementInfo(
                url = url + POST_SUBJECT_MANAGEMENT_INFO,
                "${TokenConstants.BEARER} $sAuthorization", postSubjectManagementInfo
            )
        )

    suspend fun amendSubjectManagementInfo(
        url: String,
        sAuthorization: String,
        postSubjectManagementInfo: PostSubjectManagementInfo
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendSubjectManagementInfo(
                url = url + AMEND_SUBJECT_MANAGEMENT_INFO,
                "${TokenConstants.BEARER} $sAuthorization", postSubjectManagementInfo
            )
        )

    suspend fun checkDuplicateSubjectManagementInfo(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iSubjectID: Int,
        iYearID: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateSubjectManagementInfo(
                url = url + CHECK_DUPLICATE_SUBJECT_MANAGEMENT_INFO,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassID,
                iSubjectID,
                iYearID
            )
        )

    suspend fun populateSubjectList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iYearID: Int,
        iStatusID: Int
    ): Either<Failure, MutableList<PopulateSubjectList>> =
        Either.Right(
            academicsApi.populateSubjectList(
                url = url + POPULATE_SUBJECT_LIST_SUBJECT_MANAGEMENT_INFO,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassID,
                iYearID,
                iStatusID
            )
        )

    suspend fun retrieveSubjectList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iYearID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveSubjectInfoItems>> =
        Either.Right(
            academicsApi.retrieveSubjectList(
                url = url + RETRIEVE_SUBJECT_LIST_SUBJECT_MANAGEMENT_INFO,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassID,
                iYearID,
                iStatusID
            )
        )

    suspend fun retrieveSubjectDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<RetrieveSubjectInfoItems>> =
        Either.Right(
            academicsApi.retrieveSubjectDetails(
                url = url + RETRIEVE_SUBJECT_LIST_MAP_MAP_DETAILS_SUBJECT_MANAGEMENT_INFO,
                "${TokenConstants.BEARER} $sAuthorization", id
            )
        )

    suspend fun populateSubjectProgramList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iStatusID: Int
    ): Either<Failure, List<PopulateSubjectProgramList>> =
        Either.Right(
            academicsApi.populateSubjectProgramList(
                url = url + POPULATE_SUBJECT_PROGRAM_LIST,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iStatusID
            )
        )

    suspend fun setStatusSubjectManagement(
        url: String,
        sAuthorization: String,
        iStatusID: Int,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.setStatusSubjectManagement(
                url = url + SET_ACTIVATION_STATUS_SUBJECT_MANAGEMENT_INFO,
                "${TokenConstants.BEARER} $sAuthorization",
                iStatusID, id
            )
        )
}