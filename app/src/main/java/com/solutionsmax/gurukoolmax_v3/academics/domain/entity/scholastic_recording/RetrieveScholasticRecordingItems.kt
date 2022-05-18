package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording

import com.google.gson.annotations.SerializedName

data class RetrieveScholasticRecordingItems(
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
    @SerializedName("STUDENT_ID")
    val iStudentID: Int,
    @SerializedName("STUDENT_NAME")
    val sStudentName: String,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("SUBJECT_NAME")
    val sSubjectName: String,
    @SerializedName("SCHOLASTIC_CATEGORY_ID")
    val iScholasticCategoryID: Int,
    @SerializedName("SCHOLASTIC_CATEGORY")
    val sScholasticCategory: String,
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