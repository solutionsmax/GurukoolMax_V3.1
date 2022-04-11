package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_schedule

import com.google.gson.annotations.SerializedName

data class PostBusScheduleItem(
    @SerializedName("ID")
    var id: Int? = 0,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("BUS_STOP_ID")
    val iBusStopID: Int,
    @SerializedName("ARRIVAL_TIME_HR")
    val sArrivalTimeHR: String,
    @SerializedName("ARRIVAL_TIME_MM")
    val sArrivalTimeMM: String,
    @SerializedName("TIME_CYCLE_ID")
    val iTimeCycleID: String,
    @SerializedName("START_DATE_RANGE")
    val sStartDateRange: String,
    @SerializedName("END_DATE_RANGE")
    val sEndDateRange: String,
    @SerializedName("ROUTE_ID")
    val iRouteID: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)