package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km

import com.google.gson.annotations.SerializedName

data class PostKMInfo(
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
    @SerializedName("SUBJECT_ID")
    val iSubjectID: Int,
    @SerializedName("SUBMITTER_ID")
    val iSubmitterID: Int,
    @SerializedName("TITLE")
    val sTitle: String,
    @SerializedName("ABSTRACT")
    val sAbstract: String,
    @SerializedName("CONTENT_DESCRIPTION")
    val sContentDescription: String,
    @SerializedName("FORMAT_TYPE_ID")
    val iFormatTypeID: Int,
    @SerializedName("CONTENT_TYPE_ID")
    val iContentTypeID: Int,
    @SerializedName("SOURCE_ID")
    val iSourceID: Int,
    @SerializedName("CONTENT_LINK")
    val sContentLink: String,
    @SerializedName("FILE_NAME")
    val sFileName: String,
    @SerializedName("TAG_KEYWORDS")
    val sTagKeyWords: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
