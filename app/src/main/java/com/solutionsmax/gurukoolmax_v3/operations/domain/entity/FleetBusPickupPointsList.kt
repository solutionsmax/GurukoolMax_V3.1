package com.solutionsmax.gurukoolmax_v3.operations.domain.entity

import com.google.gson.annotations.SerializedName

data class FleetBusPickupPointsList(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("BUS_STOP_NAME")
    val sBusStopName: String,
    @SerializedName("BUS_ROUTE_ID")
    val iBusRouteID: Int,
    @SerializedName("ROUTE_NAME")
    val sRouteName: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("WORKFLOW_STATUS")
    val sWorkflowStatus: String,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
