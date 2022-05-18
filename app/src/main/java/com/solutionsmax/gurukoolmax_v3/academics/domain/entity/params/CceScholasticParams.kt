package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic.PostCceScholasticItem

data class PostCceScholasticParams(
    val url: String,
    val sAuthorization: String,
    val postCceScholasticItem: PostCceScholasticItem
)

data class CheckDuplicateScholasticGradingAll(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iMinScore: Int,
    val iMaxScore: Int,
    val sGrade: String,
    val dGradePoint: Double
)

data class CheckDuplicateScholasticGrading(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val sGrade: String
)

data class CheckDuplicateScholasticMaxMinGrading(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iRangeValue: Int
)

data class CheckDuplicateScholasticGradPointGrading(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val dGradePoint: Double
)

data class IdScholasticGrading(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class RetrieveListScholasticGrading(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iStatusID: Int
)

data class RetrieveGradeByListScholasticGrading(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iMarks: Int,
    val iStatusID: Int
)
