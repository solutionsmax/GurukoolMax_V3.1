package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_result

import com.google.gson.annotations.SerializedName

data class PostExamResultItem(
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
    @SerializedName("EXAM_CATEGORY_ID")
    val iExamCategoryID: Int,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("STUDENT_ID")
    val iStudentID: Int,
    @SerializedName("MARKS_SECURED")
    val iMarksSecured: Int,
    @SerializedName("REMARKS_COMMENTS")
    val sRemarksComments: String,
    @SerializedName("SCHEDULE_NAME_ID")
    val iScheduleNameID: Int,
    @SerializedName("USER_ID")
    val iUserID: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)