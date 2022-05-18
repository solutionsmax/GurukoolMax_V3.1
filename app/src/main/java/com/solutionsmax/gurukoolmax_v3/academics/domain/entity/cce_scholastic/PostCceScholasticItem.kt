package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic

import com.google.gson.annotations.SerializedName

data class PostCceScholasticItem(
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
    @SerializedName("MARK_RANGE")
    val sMarkRange: String,
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
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
