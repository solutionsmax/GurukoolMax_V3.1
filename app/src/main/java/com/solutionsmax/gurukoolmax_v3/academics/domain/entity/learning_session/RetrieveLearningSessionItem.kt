package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.learning_session

import com.google.gson.annotations.SerializedName

data class RetrieveLearningSessionItem(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("ACADEMICS_BOARD_ID")
    val iAcademicBoardID: Int,
    @SerializedName("ACADEMICS_BOARD")
    val sAcademicBoard: String,
    @SerializedName("CLASS_STANDARD_ID")
    val iClassStandardID: Int,
    @SerializedName("CLASS_STANDARD")
    val sClassStandard: String,
    @SerializedName("SHORT_CLASS_REFERENCE")
    val sShortClassReference: String,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("SUBJECT_NAME")
    val sSubjectName: String,
    @SerializedName("ACADEMIC_YEAR_ID")
    val iAcademicYearID: Int,
    @SerializedName("ACADEMIC_YEAR")
    val sAcademicYear: String,
    @SerializedName("CURRICULUM_SECTION_ID")
    val iCurriculumSectionID: Int,
    @SerializedName("CURRICULUM_SECTION")
    val sCurriculumSection: String,
    @SerializedName("SESSION_TOPIC_ID")
    val iSessionTopicID: Int,
    @SerializedName("CURRICULUM_SECTION_TOPIC")
    val sCurriculumSectionTopic: String,
    @SerializedName("SESSION_TOPIC_DETAILS")
    val sSessionTopicDetails: String,
    @SerializedName("FACULTY_ID")
    val iFacultyID: Int,
    @SerializedName("FACULTY_NAME")
    val sFacultyName: String,
    @SerializedName("START_DATE")
    val sStartDate: String,
    @SerializedName("START_DATE_FORMAT")
    val sStartDateFormat: String,
    @SerializedName("COMPLETION_DATE")
    val sCompletionDate: String,
    @SerializedName("COMPLETION_DATE_FORMAT")
    val sCompletionDateFormat: String,
    @SerializedName("FACULTY_REMARKS")
    val sFacultyRemarks: String,
    @SerializedName("PROGRESS_STATUS")
    val iProgress: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("CREATE_DATE_FORMAT")
    val sCreateDateFormat: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
