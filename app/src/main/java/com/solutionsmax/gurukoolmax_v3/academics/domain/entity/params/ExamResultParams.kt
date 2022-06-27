package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_result.PostExamResultItem

data class PostExamResultParams(
    val url: String,
    val sAuthorization: String,
    val postExamResultItem: PostExamResultItem
)

data class CheckDuplicateExamResultParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iYearID: Int,
    val iScheduledID: Int,
    val iSubjectID: Int,
    val iStudentID: Int
)

data class RetrieveExamResultDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class RetrieveExamResultListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iYearID: Int,
    val iSubjectID: Int,
    val iStudentID: Int,
    val iScheduledID: Int,
    val iStatusID: Int
)