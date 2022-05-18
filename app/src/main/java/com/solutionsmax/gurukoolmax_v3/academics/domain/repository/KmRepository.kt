package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.PostKMInfo
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.RetrieveKMInfo
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class KmRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {

    suspend fun postKMInfo(
        url: String,
        sAuthorization: String,
        postKMInfo: PostKMInfo
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postKMInfo(
                url = url + MethodConstants.POST_KM_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postKMInfo
            )
        )

    suspend fun amendKMInfo(
        url: String,
        sAuthorization: String,
        postKMInfo: PostKMInfo
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendKMInfo(
                url = url + MethodConstants.AMEND_KM_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postKMInfo
            )
        )

    suspend fun setStatusKMInfo(
        url: String,
        sAuthorization: String,
        iStatusID: Int,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.setStatusKMInfo(
                url = url + MethodConstants.SET_STATUS_KM_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iStatusID, id
            )
        )

    suspend fun postFileNameKMInfo(
        url: String,
        sAuthorization: String,
        sFileName: String,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postFileNameKMInfo(
                url = url + MethodConstants.POST_KM_FILE_NAME_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                sFileName, id
            )
        )

    suspend fun removeKMInfo(
        url: String,
        sAuthorization: String,
        id: Int,
        iSubmitterID: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.removeKMInfo(
                url = url + MethodConstants.REMOVE_KM_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id, iSubmitterID
            )
        )

    suspend fun retrieveKMDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<RetrieveKMInfo>> =
        Either.Right(
            academicsApi.retrieveKMDetails(
                url = url + MethodConstants.RETRIEVE_KM_DETAILS,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveKMList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassStandardID: Int,
        iSubjectID: Int,
        iFormateTypeID: Int,
        iContentTypeID: Int,
        iSourceID: Int,
        iSubmitterID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveKMInfo>> =
        Either.Right(
            academicsApi.retrieveKMList(
                url = url + MethodConstants.RETRIEVE_KM_LIST,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassStandardID,
                iSubjectID,
                iFormateTypeID,
                iContentTypeID,
                iSourceID,
                iSubmitterID,
                iStatusID
            )
        )

    suspend fun retrieveKMAdvanceSearch(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassStandardID: Int,
        iSubjectID: Int,
        sSearchKey: String,
        iContentTypeID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveKMInfo>> =
        Either.Right(
            academicsApi.retrieveKMAdvanceSearch(
                url = url + MethodConstants.RETRIEVE_KM_ADVANCE_SEARCH,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassStandardID,
                iSubjectID, sSearchKey, iContentTypeID, iStatusID
            )
        )

    suspend fun retrieveKMSearch(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassStandardID: Int,
        sSearchKey: String,
        iStatusID: Int
    ): Either<Failure, List<RetrieveKMInfo>> =
        Either.Right(
            academicsApi.retrieveKMSearch(
                url = url + MethodConstants.RETRIEVE_KM_SEARCH,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iBoardID,
                iClassStandardID, sSearchKey, iStatusID
            )
        )

}