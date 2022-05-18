package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PostExamScheduleItem

data class PostExamScheduleParams(
    val url: String,
    val sAuthorization: String,
    val postExamScheduleItem: PostExamScheduleItem
)

data class SetStatusExamScheduleParams(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int,
    val id: Int
)

data class CheckDuplicateExamScheduleParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iYearID: Int,
    val sScheduleName: String
)

data class PopulateExamScheduleParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iYearID: Int,
    val iStatusID: Int
)

data class RetrieveDetailsExamScheduleParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class RetrieveListExamScheduleParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iStatusID: Int
)