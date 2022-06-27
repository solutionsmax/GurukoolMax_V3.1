package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.learning_session.PostLearningSessionItem

data class PostLearningSessionParams(
    val url: String,
    val sToken: String,
    val postLearningSessionItem: PostLearningSessionItem
)

data class AmendLearningSessionProgressParams(
    val url: String,
    val sToken: String,
    val sFacultyRemarks: String,
    val iProgressStatusID: Int,
    val dCompletionDate: String,
    val iStatusID: Int,
    val id: Int
)

data class CheckDuplicateLearningSessionProgressParams(
    val url: String,
    val sToken: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassStandardID: Int,
    val iYearID: Int,
    val iSubjectID: Int,
    val iSectionID: Int,
    val iSessionTopicID: Int,
    val sTopicDetails: String
)

data class RetrieveCurriculumTopicListByFacultyParams(
    val url: String,
    val sToken: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassStandardID: Int,
    val iYearID: Int,
    val iSubjectID: Int,
    val iSectionID: Int,
    val iFacultyID: Int,
    val iStatusID: Int
)

data class RetrieveLearningSessionDetailsParams(
    val url: String,
    val sToken: String,
    val id: Int
)

data class RetrieveLearningSessionListParams(
    val url: String,
    val sToken: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iBoardID: Int,
    val iClassStandardID: Int,
    val iSubjectID: Int,
    val iCurriculumID: Int,
    val iFacultyID: Int,
    val iStatusID: Int
)

data class SetLearningSessionStatusParams(
    val url: String,
    val sToken: String,
    val iStatusID: Int,
    val id: Int
)