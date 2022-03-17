package com.solutionsmax.gurukoolmax_v3.operations.domain.entity

import com.google.gson.annotations.SerializedName

data class FleetPickupScheduleList(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("BUS_STOP_ID")
    val iBusStopID: Int,
    @SerializedName("BUS_STOP_NAME")
    val sBusStopName: String,
    @SerializedName("ARRIVAL_TIME_HR")
    val sArrivalTimeHR: String,
    @SerializedName("ARRIVAL_TIME_HR_VALUE")
    val sArrivalTimeHRValue: String,
    @SerializedName("ARRIVAL_TIME_MM")
    val sArrivalTimeMM: String,
    @SerializedName("ARRIVAL_TIME_MM_VALUE")
    val sArrivalTimeMMValue: String,
    @SerializedName("TIME_CYCLE_ID")
    val iTimeCycleID: Int,
    @SerializedName("TIME_CYCLE")
    val sTimeCycle: String,
    @SerializedName("ARRIVAL_TIME")
    val sArrivalTime: String,
    @SerializedName("START_DATE_RANGE")
    val sStartDateRange: String,
    @SerializedName("END_DATE_RANGE")
    val sEndDateRange: String,
    @SerializedName("ROUTE_ID")
    val sRouteID: Int,
    @SerializedName("ROUTE_NAME")
    val sRouteName: String,
    @SerializedName("EFFECTIVE_DATES")
    val sEffectiveDates: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("WORKFLOW_STATUS")
    val sWorkflowStatus: String
)
