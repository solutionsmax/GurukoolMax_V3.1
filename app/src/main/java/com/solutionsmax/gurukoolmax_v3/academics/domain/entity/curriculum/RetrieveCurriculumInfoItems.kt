package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum

import com.google.gson.annotations.SerializedName

data class RetrieveCurriculumInfoItems(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("GROUP_ID")
    val iGroupId: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("COMPLETE_COUNT")
    val iCompleteCount: Int,
    @SerializedName("UN_COMPLETE_COUNT")
    val iUnCompleteCount: Int,
    @SerializedName("CLASS_GRADE_ID")
    val iClassGradeId: Int,
    @SerializedName("CLASS_GRADE")
    val sClassGrade: String,
    @SerializedName("SHORT_CLASS_REFERENCE")
    val sShortClassRef: String,
    @SerializedName("ACADEMICS_BOARD_ID")
    val iAcademicBoardID: Int,
    @SerializedName("ACADEMICS_BOARD")
    val sAcademicBoard: String,
    @SerializedName("SORT_ORDER")
    val iSortOrder: Int,
    @SerializedName("LECTURES_COUNT")
    val iLectureCount: Int,
    @SerializedName("TUTORIALS_COUNT")
    val iTutorialCount: Int,
    @SerializedName("EXTENDED_TUTORIALS_COUNT")
    val iExtendedTutorialCount: Int,
    @SerializedName("PRACTICALS_COUNT")
    val iPracticalCount: Int,
    @SerializedName("PROJECTS_COUNT")
    val iProjectCount: Int,
    @SerializedName("OUTSIDE_CLASSROOM_COUNT")
    val iOutsideClassroomCount: Int,
    @SerializedName("TOTAL_CREDITS_COUNT")
    val iTotalCreditCount: Int,
    @SerializedName("COURSE_CODE")
    val sCourseCode: String,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("SUBJECT_NAME")
    val sSubjectName: String,
    @SerializedName("SECTION_TITLE")
    val sSectionTitle: String,
    @SerializedName("SYLLABUS_DETAILS")
    val sSyllabusDetails: String,
    @SerializedName("MARK_FOCUS")
    val iMarkFocus: Int,
    @SerializedName("SCHEDULED_DURATION")
    val iScheduledDuration: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String
)