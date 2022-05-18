package com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project

import com.google.gson.annotations.SerializedName

data class PostProjectInfoItem(
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
    @SerializedName("PROJECT_NAME")
    val sProjectName: String,
    @SerializedName("TECHNOLOGY_INVOLVED")
    val sTechnologyInvolved: String,
    @SerializedName("PROJECT_ABSTRACT")
    val sProjectAbstract: String,
    @SerializedName("PROJECT_DESCRIPTION")
    val sProjectDescription: String,
    @SerializedName("SUGGESTED_BY")
    val sSuggestedBy: String,
    @SerializedName("PROPOSAL_AGENCY")
    val sProposalAgency: String,
    @SerializedName("APPROVED_BY_ID")
    val iApprovedByID: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("PROJECT_GUIDE")
    val sProjectGuide: String,
    @SerializedName("COURSE_NAME")
    val sCourseName: String,
    @SerializedName("REMARKS_PROJECT_GUIDE")
    val sRemarksProjectGuide: String,
    @SerializedName("REMARKS_DEAN")
    val sRemarksDean: String,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
