package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.PostScholasticRecordingInfoItem

data class PostScholasticRecordingParams(
    val url: String,
    val sAuthorization: String,
    val postScholasticRecordingInfoItem: PostScholasticRecordingInfoItem
)

data class CheckDuplicateScholasticRecordingParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iSubjectID: Int,
    val iStudentID: Int,
    val iScholarCategoryID: Int
)

data class PopulateStudentScholasticListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iCategoryID: Int,
    val iStatusID: Int
)

data class RetrieveScholasticRecordingDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class RetrieveScholasticRecordingListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iStatusID: Int
)