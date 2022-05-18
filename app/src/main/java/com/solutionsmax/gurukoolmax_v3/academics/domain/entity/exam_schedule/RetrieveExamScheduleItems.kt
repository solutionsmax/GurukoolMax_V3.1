package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule

import com.google.gson.annotations.SerializedName

data class RetrieveExamScheduleItems(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("GROUP_NAME")
    val sGroupName: String,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("SCHOOL_NAME")
    val sSchoolName: String,
    @SerializedName("ACADEMICS_BOARD_ID")
    val iAcademicBoardID: Int,
    @SerializedName("ACADEMICS_BOARD")
    val sAcademicBoard: String,
    @SerializedName("CALENDAR_YEAR_ID")
    val iCalendarYearID: Int,
    @SerializedName("CALENDAR_YEAR")
    val sCalendarYear: String,
    @SerializedName("SCHEDULE_NAME")
    val sScheduleName: String,
    @SerializedName("NOTES")
    val sNotes: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)