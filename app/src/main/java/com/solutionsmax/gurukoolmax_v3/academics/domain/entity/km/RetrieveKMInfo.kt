package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km

import com.google.gson.annotations.SerializedName

data class RetrieveKMInfo(
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
    @SerializedName("SUBMITTER_ID")
    val iSubmitterID: Int,
    @SerializedName("CLASS_STANDARD_ID")
    val iClassStandardID: Int,
    @SerializedName("CLASS_STANDARD")
    val sClassStandard: String,
    @SerializedName("SHORT_CLASS_REFERENCE")
    val sShortClassReference: String,
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("SUBJECT_NAME")
    val sSubjectName: String,
    @SerializedName("FORMAT_TYPE_ID")
    val iFormatTypeID: Int,
    @SerializedName("FORMAT_TYPE")
    val sFormatType: String,
    @SerializedName("CONTENT_TYPE_ID")
    val iContentTypeID: Int,
    @SerializedName("CONTENT_TYPE")
    val sContentType: String,
    @SerializedName("SOURCE_ID")
    val iSourceID: Int,
    @SerializedName("SOURCE_CATEGORY")
    val sSourceCategory: String,
    @SerializedName("TAG_KEYWORDS")
    val sTagKeyWords: String,
    @SerializedName("TITLE")
    val sTitle: String,
    @SerializedName("ABSTRACT")
    val sAbstract: String,
    @SerializedName("CONTENT_DESCRIPTION")
    val sContentDescription: String,
    @SerializedName("CONTENT_LINK")
    val sContentLink: String,
    @SerializedName("SOURCE_INFO")
    val sSourceInfo: String,
    @SerializedName("FILE_NAME")
    val sFileName: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("WORKFLOW_STATUS")
    val sWorkflowStatus: String,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)