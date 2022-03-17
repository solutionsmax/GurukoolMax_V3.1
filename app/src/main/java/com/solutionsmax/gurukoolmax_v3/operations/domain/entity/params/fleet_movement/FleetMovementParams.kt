package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_movement

import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPostInfoItem

data class FleetMovementPostParams(
    val url:String,
    val sAuthorization: String,
    val fleetMovementPostInfoItem: FleetMovementPostInfoItem
)

data class FleetMovementDuplicate(
    val url:String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iVehicleID: Int,
    val iOpeningReading: Int,
    val dMovementDate: String
)

data class FleetMovementFetchWorkflowStatus(
    val url:String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iVehicleID: Int,
    val iWorkflowStatusID: Int
)

data class FleetMovementRetrieveDetails(
    val url:String,
    val sAuthorization: String,
    val id: Int
)

data class FleetMovementRetrieveList(
    val url:String,
    val sAuthorization: String,
    val iGroupID: Int,
    val iSchoolID: Int,
    val iWorkflowStatusID: Int
)

data class FleetMovementSetStatus(
    val url: String,
    val sAuthorization: String,
    val iStatusID: Int,
    val id: Int
)