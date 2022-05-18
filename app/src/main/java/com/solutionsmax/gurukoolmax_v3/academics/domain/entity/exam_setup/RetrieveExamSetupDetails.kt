package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup

import com.google.gson.annotations.SerializedName

data class RetrieveExamSetupDetails(
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
    @SerializedName("CALENDAR_YEAR_ID")
    val iCalendarYearID: Int,
    @SerializedName("CALENDAR_YEAR")
    val sCalendarYear: String,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("SUBJECT_NAME")
    val sSubjectName: String,
    @SerializedName("SCHEDULE_NAME_ID")
    val iScheduleNameID: Int,
    @SerializedName("SCHEDULE_NAME")
    val sScheduleName: String,
    @SerializedName("DATE_OF_EXAM")
    val sDateOfExam: String,
    @SerializedName("DATE_OF_EXAM_DISPLAY")
    val sDateOfExamDisplay: String,
    @SerializedName("FOCUS_ASSIGNMENT_ID")
    val iFocusAssignmentID: Int,
    @SerializedName("FOCUS_ASSIGNMENT")
    val sFocusAssignment: String,
    @SerializedName("START_TIME")
    val sStartTime: String,
    @SerializedName("END_TIME")
    val sEndTime: String,
    @SerializedName("ASSIGNMENT_DETAILS")
    val sAssignmentDetails: String,
    @SerializedName("MARKS_ALLOCATED")
    val iMarksAllocated: Int,
    @SerializedName("REMARKS_COMMENTS")
    val sRemarksComments: String,
    @SerializedName("APPROVAL_STATUS")
    val sApprovalStatus: String,
    @SerializedName("APPROVED_BY_ID")
    val iApprovedById: Int,
    @SerializedName("APPROVED_BY")
    val sApprovedBy: String,
    @SerializedName("APPROVED_DATE")
    val sApprovedDate: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
