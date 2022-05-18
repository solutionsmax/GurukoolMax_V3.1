package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management

import com.google.gson.annotations.SerializedName

data class RetrieveSubjectInfoItems(
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
    @SerializedName("COURSE_CODE")
    val sCourseCode: String,
    @SerializedName("SUBJECT_NAME")
    val sSubjectName: String,
    @SerializedName("REMARKS")
    val sRemarks: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
