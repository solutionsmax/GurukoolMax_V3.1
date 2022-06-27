package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup.PostExamSetupInfoItem

data class PostExamSetupParams(
    val url: String,
    val sAuthorization: String,
    val postExamSetupInfoItem: PostExamSetupInfoItem
)

data class CheckDuplicateExamParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iYearID: Int,
    val iScheduledNameID: Int,
    val iSubjectID: Int,
    val tStartTime: String,
    val tEndTime: String,
    val dExamDate: String,
    val iStatusID: Int
)

data class RetrieveExamSetupDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class RetrieveExamSetupListParams(
    val url: String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassID: Int,
    val iYearID: Int,
    val iScheduledID: Int,
    val iSubjectID: Int,
    val iAssessmentID: Int,
    val iStatusID: Int,
    val iStudentID: Int
)

data class SetExamSetupStatusParams(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int,
    val id: Int
)