package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic

import com.google.gson.annotations.SerializedName

data class RetrieveCceScholasticItem(
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
    @SerializedName("CLASS_GRADE_ID")
    val iClassGradeID: Int,
    @SerializedName("CLASS_GRADE")
    val sClassGrade: String,
    @SerializedName("SHORT_CLASS_REFERENCE")
    val sShortClassRef: String,
    @SerializedName("MARK_RANGE")
    val sMaxRange: String,
    @SerializedName("MIN_SCORE_RANGE")
    val iMinScoreRange: Int,
    @SerializedName("MAX_SCORE_RANGE")
    val iMaxScoreRange: Int,
    @SerializedName("GRADE")
    val sGrade: String,
    @SerializedName("GRADE_POINT")
    val iGradePoint: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCrateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
