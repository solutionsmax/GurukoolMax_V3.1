package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.PostProjectInfoItem

data class PostAcademicProjectParams(
    val url: String,
    val sAuthorization: String,
    val postProjectInfoItem: PostProjectInfoItem
)

data class AcademicProjectGuideParams(
    val url: String,
    val sAuthorization: String,
    val sGuide: String,
    val sCourseName: String,
    val sGuideRemarks: String,
    val sRemarksDean: String,
    val id: Int
)

data class AcademicProjectSetStatusParams(
    val url: String,
    val sAuthorization: String,
    val iStatusID: String,
    val id: Int
)

data class AcademicProjectCheckDuplicateParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassStandardID: Int,
    val sProjectName: String
)

data class AcademicProjectRetrieveDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class AcademicProjectRetrieveListParams(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int
)

data class AcademicProjectSearchParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardiD: Int,
    val iClassStandardID: Int,
    val sProjectName: String,
    val sTechnology: String,
    val sSuggestedBy: String,
    val sAgency: String,
    val sSearchKey: String,
    val iStatusID: Int
)
