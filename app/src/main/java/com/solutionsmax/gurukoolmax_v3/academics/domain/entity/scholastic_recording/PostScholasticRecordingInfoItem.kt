package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording

import com.google.gson.annotations.SerializedName

data class PostScholasticRecordingInfoItem(
    @SerializedName("ID")
    var id: Int? = 0,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("STUDENT_ID")
    val iStudentID: Int,
    @SerializedName("ACADEMICS_BOARD_ID")
    val iAcademicBoardID: Int,
    @SerializedName("CLASS_STANDARD_ID")
    val iClassStandardID: Int,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("SCHOLASTIC_CATEGORY_ID")
    val iScholasticCategoryID: Int,
    @SerializedName("MARKS_SECURED")
    val iMarksSecured: Int,
    @SerializedName("USER_ID")
    val iUserID: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
