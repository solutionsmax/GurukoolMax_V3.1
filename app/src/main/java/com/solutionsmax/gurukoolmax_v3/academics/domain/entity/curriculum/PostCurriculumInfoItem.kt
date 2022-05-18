package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum

import com.google.gson.annotations.SerializedName

data class PostCurriculumInfoItem(
    @SerializedName("ID")
    var id: Int? = 0,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("ACADEMICS_BOARD_ID")
    val iAcademicBoardID: Int,
    @SerializedName("CLASS_GRADE_ID")
    val iClassGradeID: Int,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("SECTION_TITLE")
    val sSectionTitle: String,
    @SerializedName("SYLLABUS_DETAILS")
    val sSyllabusDetails: String,
    @SerializedName("MARK_FOCUS")
    val iMarksFocus: Int,
    @SerializedName("SCHEDULED_DURATION")
    val iScheduleDuration: Int,
    @SerializedName("LECTURES_COUNT")
    val iLecturesCount: Int,
    @SerializedName("TUTORIALS_COUNT")
    val iTutorialCount: Int,
    @SerializedName("EXTENDED_TUTORIALS_COUNT")
    val iExtendedTutorialCount: Int,
    @SerializedName("PRACTICALS_COUNT")
    val iPracticalCount: Int,
    @SerializedName("PROJECTS_COUNT")
    val iProjectCount: Int,
    @SerializedName("OUTSIDE_CLASSROOM_COUNT")
    val iOutsideClassRoomCount: Int,
    @SerializedName("TOTAL_CREDITS_COUNT")
    val iTotalCreditCount: Int,
    @SerializedName("SORT_ORDER")
    val iSortOrder: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
