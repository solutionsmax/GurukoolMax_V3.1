package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.learning_session

import com.google.gson.annotations.SerializedName

data class PostLearningSessionItem(
    @SerializedName("ID")
    var id: Int? = 0,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("ACADEMICS_BOARD_ID")
    val iAcademicBoardID: Int,
    @SerializedName("CLASS_STANDARD_ID")
    val iClassStandardID: Int,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("CURRICULUM_SECTION_ID")
    val iCurriculumSectionID: Int,
    @SerializedName("SESSION_TOPIC_ID")
    val iSessionTopicID: Int,
    @SerializedName("SESSION_TOPIC_DETAILS")
    val sSessionTopicDetails: String,
    @SerializedName("FACULTY_ID")
    val iFacultyID: Int,
    @SerializedName("START_DATE")
    val sStartDate: String,
    @SerializedName("COMPLETION_DATE")
    val sCompletionDate: String,
    @SerializedName("FACULTY_REMARKS")
    val sFacultyRemarks: String,
    @SerializedName("PROGRESS_STATUS")
    val iProgressStatus: Int,
    @SerializedName("ACADEMIC_YEAR_ID")
    val iAcademicYearID: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
