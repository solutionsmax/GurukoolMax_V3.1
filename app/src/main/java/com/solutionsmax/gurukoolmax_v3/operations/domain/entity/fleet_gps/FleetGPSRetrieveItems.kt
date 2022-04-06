package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps

import com.google.gson.annotations.SerializedName

data class FleetGPSRetrieveItems(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("ROUTE_ID")
    val iRouteID: Int,
    @SerializedName("DRIVER_ID")
    val iDriverID: Int,
    @SerializedName("USER_NAME")
    val sUsername: String,
    @SerializedName("FULL_NAME")
    val sFullName: String,
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
    @SerializedName("LATITUDE")
    val sLatitude: String,
    @SerializedName("LONGITUDE")
    val sLongitude: String,
    @SerializedName("SYNC_DATE_TIME")
    val sSyncDateTime: String,
    @SerializedName("IND_DATETIME")
    val sIndDateTime: String,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String
)
