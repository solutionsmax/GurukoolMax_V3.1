package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement

import com.google.gson.annotations.SerializedName

data class FleetMovementRetrieveItem(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("GROUP_ID")
    val iGroupId: Int,
    @SerializedName("BRANCH_ID")
    val iBranchID: Int,
    @SerializedName("VEHICLE_ID")
    val iVehicleID: Int,
    @SerializedName("VEHICLE_NAME")
    val sVehicleName: String,
    @SerializedName("REGISTRATION_NUMBER")
    val sRegistrationNumber: String,
    @SerializedName("DATE_OF_RECORD")
    val sDateOfRecord: String,
    @SerializedName("OPENING_READING")
    val iOpeningReading: Int,
    @SerializedName("CLOSING_READING")
    val iClosingReading: Int,
    @SerializedName("TRIP_READING")
    val iTripReading: Int,
    @SerializedName("FLEET_NUMBER")
    val sFleetNumber: String,
    @SerializedName("FUEL_COUPON_NUMBER")
    val sFleetCouponNumber: String,
    @SerializedName("FUEL_LITRES")
    val dFuelLiters: Double,
    @SerializedName("AMOUNT")
    val dAmount: Double,
    @SerializedName("REMARKS")
    val sRemarks: String,
    @SerializedName("USER_ID")
    val iUserID: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String

)