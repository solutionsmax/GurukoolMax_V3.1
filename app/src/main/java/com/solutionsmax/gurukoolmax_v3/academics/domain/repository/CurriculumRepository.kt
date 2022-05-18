package com.solutionsmax.gurukoolmax_v3.academics.domain.repository

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.PopulateCurriculumSessionTopicList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.PostCurriculumInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.RetrieveCurriculumInfoItems
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.AMEND_CURRICULUM_SETUP_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_CURRICULUM_SETUP_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_LIST_BY_CLASS_REFERENCE_CURRICULUM_SETUP_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_SESSION_TOPIC_LIST_CURRICULUM_SETUP_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POST_CURRICULUM_SETUP_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_DETAILS_CURRICULUM_SETUP_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_LIST_CURRICULUM_SETUP_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.AcademicsApi
import javax.inject.Inject

class CurriculumRepository @Inject constructor(
    private val academicsApi: AcademicsApi
) {

    suspend fun postCurriculumInfo(
        url: String,
        sAuthorization: String,
        postCurriculumInfoItem: PostCurriculumInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.postCurriculumInfo(
                url = url + POST_CURRICULUM_SETUP_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postCurriculumInfoItem = postCurriculumInfoItem
            )
        )

    suspend fun amendCurriculumInfo(
        url: String,
        sAuthorization: String,
        postCurriculumInfoItem: PostCurriculumInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.amendCurriculumInfo(
                url = url + AMEND_CURRICULUM_SETUP_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                postCurriculumInfoItem = postCurriculumInfoItem
            )
        )

    suspend fun checkDuplicateCurriculumInfo(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iGradeID: Int,
        sSectionTitle: String,
        iSubjectID: Int
    ): Either<Failure, Int> =
        Either.Right(
            academicsApi.checkDuplicateCurriculumInfo(
                url = url + CHECK_DUPLICATE_CURRICULUM_SETUP_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iGradeID, sSectionTitle, iSubjectID
            )
        )

    suspend fun populateSessionTopicList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iGradeID: Int,
        iSubjectID: Int,
        iStatusID: Int
    ): Either<Failure, List<PopulateCurriculumSessionTopicList>> =
        Either.Right(
            academicsApi.populateSessionTopicList(
                url = url + POPULATE_SESSION_TOPIC_LIST_CURRICULUM_SETUP_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iGradeID, iSubjectID, iStatusID
            )
        )

    suspend fun populateCurriculumListByClassReference(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iGradeID: Int,
        iSubjectID: Int,
        iStatusID: Int
    ): Either<Failure, List<PopulateCurriculumSessionTopicList>> =
        Either.Right(
            academicsApi.populateCurriculumListByClassReference(
                url = url + POPULATE_LIST_BY_CLASS_REFERENCE_CURRICULUM_SETUP_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iBoardID, iGradeID, iSubjectID, iStatusID
            )
        )

    suspend fun retrieveCurriculumDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<RetrieveCurriculumInfoItems>> =
        Either.Right(
            academicsApi.retrieveCurriculumDetails(
                url = url + RETRIEVE_DETAILS_CURRICULUM_SETUP_INFO,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveCurriculumList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iBoardID: Int,
        iGradeID: Int,
        iSubjectID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveCurriculumInfoItems>> =
        Either.Right(
            academicsApi.retrieveCurriculumList(
                url = url + RETRIEVE_LIST_CURRICULUM_SETUP_INFO,
                sAuthorization, iGroupID, iSchoolID, iBoardID, iGradeID, iSubjectID, iStatusID
            )
        )
}