package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log

import com.google.gson.annotations.SerializedName

data class FuelLogsPostInfoItem(
    @SerializedName("ID")
    var id: Int? = 0,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("SCHOOL_ID")
    val iSchoolID: Int,
    @SerializedName("FLEET_ID")
    val iFleetID: Int,
    @SerializedName("DATE_OF_PURCHASE")
    val sDateOfPurchase: String,
    @SerializedName("ODOMETER")
    val iOdometer: Int,
    @SerializedName("GALLONS")
    val dGallons: Double,
    @SerializedName("TOTAL_COST")
    val dTotalCost: Double,
    @SerializedName("FUEL_TYPE_ID")
    val iFuelTypeID: Int,
    @SerializedName("PROVIDER_NAME")
    val sProviderName: String,
    @SerializedName("NOTES")
    val sNotes: String,
    @SerializedName("USER_ID")
    val iUserID: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
