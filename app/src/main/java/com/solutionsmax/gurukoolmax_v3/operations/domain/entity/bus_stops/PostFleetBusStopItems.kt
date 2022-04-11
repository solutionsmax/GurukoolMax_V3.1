package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops

import com.google.gson.annotations.SerializedName

data class PostFleetBusStopItems(
    @SerializedName("ID")
    var id: Int? = 0,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("BUS_STOP_NAME")
    val sBusStopName: String,
    @SerializedName("BUS_ROUTE_ID")
    val iBusRouteID: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)