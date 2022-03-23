package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register

import com.google.gson.annotations.SerializedName

data class FleetPostPhotoItem(
    @SerializedName("iVehicleID")
    val iVehicleID: Int,
    @SerializedName("sPhotoRef")
    val sPhotoRef: String,
    @SerializedName("sPhotoURL")
    val sPhotoURL: String,
    @SerializedName("iWorkflowStatusID")
    var iWorkflowStatusID: Int
)
