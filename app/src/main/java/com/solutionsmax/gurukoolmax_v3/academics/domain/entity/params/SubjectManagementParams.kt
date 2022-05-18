package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PostSubjectManagementInfo

data class PostSubjectInfoParams(
    val url: String,
    val sAuthorization: String,
    val postSubjectManagementInfo: PostSubjectManagementInfo
)

data class CheckDuplicateSubjectInfoParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iSubjectID: Int,
    val iYearID: Int
)

data class PopulateSubjectListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iYearID: Int,
    val iStatusID: Int
)

data class RetrieveSubjectListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iYearID: Int,
    val iStatusID: Int
)

data class RetrieveSubjectDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class PopulateSubjectProgramsListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iStatusID: Int
)