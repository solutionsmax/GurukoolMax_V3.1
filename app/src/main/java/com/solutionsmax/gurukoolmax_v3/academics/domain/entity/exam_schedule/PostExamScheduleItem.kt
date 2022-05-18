package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule

import com.google.gson.annotations.SerializedName

data class PostExamScheduleItem(
    @SerializedName("ID")
    var id: Int? = -1,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("ACADEMICS_BOARD_ID")
    val iAcademicBoardID: Int,
    @SerializedName("CALENDAR_YEAR_ID")
    val iCalendarYearID: Int,
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
