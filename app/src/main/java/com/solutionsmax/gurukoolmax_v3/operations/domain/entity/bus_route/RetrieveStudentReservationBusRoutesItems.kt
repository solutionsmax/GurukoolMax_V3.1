package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route

import com.google.gson.annotations.SerializedName

data class RetrieveStudentReservationBusRoutesItems(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("STUDENT_ID")
    val iStudentID: Int,
    @SerializedName("STUDENT_NAME")
    val sStudentName: String,
    @SerializedName("ADMISSION_NUM")
    val sAdmissionNum: String,
    @SerializedName("PICKUP_BUS_ROUTE_ID")
    val iPickupBusRouteID: Int,
    @SerializedName("PICKUP_BUS_ROUTE")
    val sPickupBusRoute: String,
    @SerializedName("DROP_BUS_ROUTE_ID")
    val iDropBusRouteID: Int,
    @SerializedName("DROP_BUS_ROUTE")
    val sDropBusRoute: String,
    @SerializedName("IS_SMS")
    val iIsSMS: Int,
    @SerializedName("IS_SMS_STATUS")
    val sIsSMSStatus: String,
    @SerializedName("NOTES")
    val sNotes: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
