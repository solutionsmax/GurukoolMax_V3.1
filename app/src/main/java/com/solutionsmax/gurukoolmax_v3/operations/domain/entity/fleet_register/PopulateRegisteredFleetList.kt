package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register

import com.google.gson.annotations.SerializedName

data class PopulateRegisteredFleetList(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("VEHICLE_NAME")
    val sVehicleName: String
){
    override fun toString(): String {
        return sVehicleName
    }
}
