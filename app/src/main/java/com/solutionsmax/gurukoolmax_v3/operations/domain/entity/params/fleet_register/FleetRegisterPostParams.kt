package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register

import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetPostPhotoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterPostInfoItem

data class FleetRegisterPostParams(
    val url: String,
    val sAuthorization: String,
    val fleetRegistrationPostInfoItem: FleetRegisterPostInfoItem
)

data class FleetDuplicateCheckParams(
    val url: String,
    val sAuthorization: String,
    val sRegistrationNumber: String
)

data class FleetRegisteredListParams(
    val url: String,
    val sAuthorization: String,
    val iWorkflowStatusID: Int
)

data class FleetRegisteredDetailsParams(
    val url: String,
    val sAuthorization: String,
    val id: Int
)

data class FetchRegisteredFleetPhoto(
    val url: String,
    val sAuthorization: String,
    val iVehicleID: Int
)

data class SetRegisteredFleetStatus(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int,
    val id: Int
)

data class PostFleetPhoto(
    val url: String,
    val sAuthorization: String,
    val fleetPostPhotoItem: FleetPostPhotoItem
)