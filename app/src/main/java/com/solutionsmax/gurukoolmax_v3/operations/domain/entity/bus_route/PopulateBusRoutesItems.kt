package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route

import com.google.gson.annotations.SerializedName

data class PopulateBusRoutesItems(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("ROUTE_NAME")
    val sRouteName: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
){
    override fun toString(): String {
        return sRouteName
    }
}
