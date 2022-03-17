package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement

import com.google.gson.annotations.SerializedName

data class FleetMovementPopulateList(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("VEHICLE_NAME")
    val sVehicleName: String,
    @SerializedName("REGISTRATION_NUMBER")
    val sRegistrationNumber: String
) {
    override fun toString(): String {
        return sVehicleName
    }
}
