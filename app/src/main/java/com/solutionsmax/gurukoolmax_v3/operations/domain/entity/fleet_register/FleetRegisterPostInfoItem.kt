package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register

import com.google.gson.annotations.SerializedName

data class FleetRegisterPostInfoItem(
    @SerializedName("ID")
    var id: Int? = 0,
    @SerializedName("GROUP_ID")
    val iGroupID: Int,
    @SerializedName("BRANCH_ID")
    val iBranchID: Int,
    @SerializedName("VEHICLE_NAME")
    val sVehicleName: String,
    @SerializedName("REGISTRATION_NUMBER")
    val sRegistrationNumber: String,
    @SerializedName("VIN_NUMBER")
    val sVinNumber: String,
    @SerializedName("PURCHASE_DATE")
    val sPurchaseDate: String,
    @SerializedName("MODEL")
    val sModel: String,
    @SerializedName("MAKE")
    val sMake: String,
    @SerializedName("MANUFACTURE_YEAR_ID")
    val iManufactureYearID: Int,
    @SerializedName("FUEL_TYPE_ID")
    val iFuelTypeID: Int,
    @SerializedName("COLOR")
    val sColor: String,
    @SerializedName("CUBIC_CAPACITY")
    val sCubicCapacity: String,
    @SerializedName("SEATING_CAPACITY")
    val iSeatingCapacity: Int,
    @SerializedName("REGISTRATION_AUTHORITY")
    val sRegistrationAuthority: String,
    @SerializedName("PHOTO_REF")
    val sPhotoRef: String,
    @SerializedName("PHOTO_URL")
    val sPhotoURL: String,
    @SerializedName("USER_ID")
    val iUserID: Int,
    @SerializedName("WORKFLOW_STATUS_ID")
    val iWorkflowStatusID: Int,
    @SerializedName("CREATE_DATE")
    val sCreateDate: String,
    @SerializedName("UPDATE_DATE")
    val sUpdateDate: String
)
