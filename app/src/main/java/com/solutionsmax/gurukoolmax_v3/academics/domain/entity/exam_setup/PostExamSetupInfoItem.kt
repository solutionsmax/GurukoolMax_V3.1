package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup

import com.google.gson.annotations.SerializedName

data class PostExamSetupInfoItem(
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
    @SerializedName("CALENDAR_YEAR_ID")
    val iCalendarYearID: Int,
    @SerializedName("SCHEDULE_NAME_ID")
    val iScheduleNameID: Int,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("FOCUS_ASSIGNMENT_ID")
    val iFocusAssignmentID: Int,
    @SerializedName("START_TIME")
    val sStartTime: String,
    @SerializedName("END_TIME")
    val sEndTime: String,
    @SerializedName("MARKS_ALLOCATED")
    val iMarksAllocated: Int,
    @SerializedName("ASSIGNMENT_DETAILS")
    val sAssignmentDetails: String,
    @SerializedName("REMARKS_COMMENTS")
    val sRemarksComments: String,
    @SerializedName("DATE_OF_EXAM")
    val sDateOfExam: String,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("APPROVED_BY_ID")
    val iApprovedByID: Int,
    @SerializedName("APPROVED_DATE")
    val sApprovedDate: String
)
