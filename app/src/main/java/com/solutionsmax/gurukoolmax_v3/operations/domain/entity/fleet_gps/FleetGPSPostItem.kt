package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps

import com.google.gson.annotations.SerializedName

data class FleetGPSPostItem(
    @SerializedName("ID")
    var id: Int? = 0,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("ROUTE_ID")
    val iRouteID: Int,
    @SerializedName("DRIVER_ID")
    val iDriverID: Int,
    @SerializedName("MOBILE_NUMBER")
    val sMobileNumber: String,
    @SerializedName("IP_ADDRESS")
    val sIpAddress: String,
    @SerializedName("ADDRESS")
    val sAddress: String,
    @SerializedName("CITY")
    val sCity: String,
    @SerializedName("STATE")
    val sState: String,
    @SerializedName("COUNTRY")
    val sCountry: String,
    @SerializedName("POSTAL_CODE")
    val sPostalCode: String,
    @SerializedName("KNOWN_NAME")
    val sKnownName: String,
    @SerializedName("LATITUDE")
    val sLatitude: String,
    @SerializedName("LONGITUDE")
    val sLongitude: String,
    @SerializedName("SYNC_DATE_TIME")
    val sSyncDateTime: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String
)
