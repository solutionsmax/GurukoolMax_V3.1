package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.PostCurriculumInfoItem

data class PostCurriculumInfoParams(
    val url: String,
    val sAuthorization: String,
    val postCurriculumInfoItem: PostCurriculumInfoItem
)

data class CheckDuplicateCurriculumInfoParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iGradeID: Int,
    val sSectionTitle: String,
    val iSubjectID: Int
)

data class PopulateCurriculumSessionTopicListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iGradeID: Int,
    val iSubjectID: Int,
    val iStatusID: Int
)

data class RetrieveCurriculumDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class RetrieveCurriculumListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iGradeID: Int,
    val iSubjectID: Int,
    val iStatusID: Int
)