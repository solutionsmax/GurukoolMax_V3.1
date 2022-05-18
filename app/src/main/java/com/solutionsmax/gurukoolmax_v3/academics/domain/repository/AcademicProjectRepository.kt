package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.PostProjectInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.RetrieveAcademicProjectItem
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class AcademicProjectRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {

    suspend fun postAcademicProjectInfo(
        url: String,
        sAuthorization: String,
        postProjectInfoItem: PostProjectInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postAcademicProjectInfo(
                url = url + MethodConstants.POST_PROJECT_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postProjectInfoItem = postProjectInfoItem
            )
        )

    suspend fun amendAcademicProjectInfo(
        url: String,
        sAuthorization: String,
        postProjectInfoItem: PostProjectInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendAcademicProjectInfo(
                url = url + MethodConstants.AMEND_PROJECT_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postProjectInfoItem = postProjectInfoItem
            )
        )

    suspend fun amendAcademicProjectGuideInfo(
        url: String,
        sAuthorization: String,
        sGuide: String,
        sCourseName: String,
        sGuideRemarks: String,
        sRemarksDean: String,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendProjectGuideInfo(
                url = url + MethodConstants.AMEND_PROJECT_GUIDE_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                sGuide, sCourseName, sGuideRemarks, sRemarksDean, id
            )
        )

    suspend fun setAcademicProjectStatus(
        url: String,
        sAuthorization: String,
        iStatusID: String,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.setAcademicProjectStatus(
                url = url + MethodConstants.SET_PROJECT_STATUS_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iStatusID, id
            )
        )

    suspend fun checkDuplicateAcademicProjectInfo(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassStandardID: Int,
        sProjectName: String
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateAcademicProjectInfo(
                url = url + MethodConstants.CHECK_DUPLICATE_PROJECT_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iClassStandardID, sProjectName
            )
        )

    suspend fun retrieveAcademicProjectDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<RetrieveAcademicProjectItem>> =
        Either.Right(
            academicsApi.retrieveAcademicProjectDetails(
                url = url + MethodConstants.RETRIEVE_DETAILS_PROJECT_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveAcademicProjectList(
        url: String,
        sAuthorization: String,
        iStatusID: Int
    ): Either<Failure, List<RetrieveAcademicProjectItem>> =
        Either.Right(
            academicsApi.retrieveAcademicProjectList(
                url = url + MethodConstants.RETRIEVE_LIST_PROJECT_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iStatusID
            )
        )

    suspend fun retrieveAcademicProjectSearchList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardiD: Int,
        iClassStandardID: Int,
        sProjectName: String,
        sTechnology: String,
        sSuggestedBy: String,
        sAgency: String,
        sSearchKey: String,
        iStatusID: Int
    ): Either<Failure, List<RetrieveAcademicProjectItem>> =
        Either.Right(
            academicsApi.retrieveAcademicProjectSearchList(
                url = url + MethodConstants.SEARCH_PROJECT_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardiD,
                iClassStandardID,
                sProjectName,
                sTechnology,
                sSuggestedBy,
                sAgency,
                sSearchKey,
                iStatusID
            )
        )

}