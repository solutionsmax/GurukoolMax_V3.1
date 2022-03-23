package com.solutionsmax.gurukoolmax_v3.core.data.master

import com.google.gson.annotations.SerializedName

data class PopulateMasterListItem(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("NAME")
    var sName: String,
    @SerializedName("DESCRIPTION")
    var sDescription: String,
    @SerializedName("SORT_ORDER")
    val iSortOrder: Int,
    @SerializedName("CATEGORY_ID")
    val iCategoryID: Int,
    @SerializedName("PARENT_ID")
    val iParentID: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int
){
    override fun toString(): String {
        return sName
    }
}
