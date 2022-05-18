package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.PopulateStudentScholasticGradingItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.PostScholasticRecordingInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.RetrieveScholasticRecordingItems
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.AMEND_SCHOLASTIC_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_SCHOLASTIC_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_SCHOLASTIC_STUDENT_LIST
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POST_SCHOLASTIC_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_SCHOLASTIC_DETAILS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_SCHOLASTIC_LIST
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class ScholasticGradingRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {

    suspend fun postScholasticInfo(
        url: String,
        sToken: String,
        postScholasticRecordingInfoItem: PostScholasticRecordingInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postScholasticInfo(
                url = "$url$POST_SCHOLASTIC_INFO",
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                postScholasticRecordingInfoItem = postScholasticRecordingInfoItem
            )
        )

    suspend fun amendScholasticInfo(
        url: String,
        sToken: String,
        postScholasticRecordingInfoItem: PostScholasticRecordingInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendScholasticInfo(
                url = "$url$AMEND_SCHOLASTIC_INFO",
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                postScholasticRecordingInfoItem = postScholasticRecordingInfoItem
            )
        )

    suspend fun checkDuplicateScholasticInfo(
        url: String,
        sToken: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iSubjectID: Int,
        iStudentID: Int,
        iScholarCategoryID: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateScholasticInfo(
                url = "$url$CHECK_DUPLICATE_SCHOLASTIC_INFO",
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                iGroupID, iSchoolID, iBoardID, iClassID, iSubjectID, iStudentID, iScholarCategoryID
            )
        )

    suspend fun populateStudentScholasticList(
        url: String,
        sToken: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iCategoryID: Int,
        iStatusID: Int
    ): Either<Failure, List<PopulateStudentScholasticGradingItem>> =
        Either.Right(
            academicsApi.populateStudentScholasticList(
                url = "$url$POPULATE_SCHOLASTIC_STUDENT_LIST",
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                iGroupID, iSchoolID, iBoardID, iClassID, iCategoryID, iStatusID
            )
        )

    suspend fun retrieveScholasticRecordingDetails(
        url: String,
        sToken: String,
        id: Int
    ): Either<Failure, List<RetrieveScholasticRecordingItems>> =
        Either.Right(
            academicsApi.retrieveScholasticRecordingDetails(
                url = "$url$RETRIEVE_SCHOLASTIC_DETAILS",
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                id
            )
        )

    suspend fun retrieveScholasticRecordingList(
        url: String,
        sToken: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iClassID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveScholasticRecordingItems>> =
        Either.Right(
            academicsApi.retrieveScholasticRecordingList(
                url = "$url$RETRIEVE_SCHOLASTIC_LIST",
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                iGroupID, iSchoolID, iBoardID, iClassID, iStatusID
            )
        )
}