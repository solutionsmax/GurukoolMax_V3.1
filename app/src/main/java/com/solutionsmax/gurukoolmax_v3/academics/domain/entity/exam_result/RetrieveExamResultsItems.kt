package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_result

import com.google.gson.annotations.SerializedName

data class RetrieveExamResultsItems(
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
    val sShortClassRef: String,
    @SerializedName("CALENDAR_YEAR_ID")
    val iCalendarYearID: Int,
    @SerializedName("CALENDAR_YEAR")
    val sCalendarYear: String,
    @SerializedName("SCHEDULE_NAME_ID")
    val iScheduleNameID: Int,
    @SerializedName("SCHEDULE_NAME")
    val sScheduleName: String,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("SUBJECT_NAME")
    val sSubjectName: String,
    @SerializedName("STUDENT_ID")
    val iStudentInt: Int,
    @SerializedName("STUDENT_NAME")
    val sStudentName: String,
    @SerializedName("MARKS_SECURED")
    val iMarksSecured: Int,
    @SerializedName("AVG_MARK")
    val dAverageMarks: Double,
    @SerializedName("REMARKS_COMMENTS")
    val sRemarksComments: String,
    @SerializedName("USER_ID")
    val iUserID: Int,
    @SerializedName("APPROVED_BY")
    val sApprovedBy: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("WORKFLOW_STATUS")
    val sWorkflowStatus: String,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String,
    @SerializedName("STUDENT_RANK")
    val iStudentRank: Int
)
