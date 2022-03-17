package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPopulateList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementRetrieveItem
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import javax.inject.Inject

class FleetMovementRepository @Inject constructor(
    private val fleetApi: FleetApi
) {

    suspend fun postFleetMovementInfo(
        url: String,
        sAuthorization: String,
        fleetMovementPostInfoItem: FleetMovementPostInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.postFleetMovementInfo(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                fleetMovementPostInfoItem = fleetMovementPostInfoItem
            )
        )

    suspend fun amendFleetMovementInfo(
        url: String,
        sAuthorization: String,
        fleetMovementPostInfoItem: FleetMovementPostInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.amendFleetMovementInfo(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                fleetMovementPostInfoItem = fleetMovementPostInfoItem
            )
        )

    suspend fun checkDuplicateFleetMovement(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iVehicleID: Int,
        iOpeningReading: Int,
        dMovementDate: String
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.checkDuplicateFleetMovementInfo(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iVehicleID = iVehicleID,
                iOpeningReading = iOpeningReading,
                dMovementDate = dMovementDate
            )
        )

    suspend fun fetchWorkflowStatusFleetMovement(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iVehicleID: Int,
        iWorkflowStatusID: Int
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.fetchWorkflowStatusFleetMovement(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iVehicleID = iVehicleID,
                iWorkflowStatusID = iWorkflowStatusID
            )
        )

    suspend fun fetchClosingRangeFleetMovement(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iVehicleID: Int,
        iWorkflowStatusID: Int,
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.fetchClosingRangeFleetMovement(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iVehicleID = iVehicleID,
                iWorkflowStatusID = iWorkflowStatusID
            )
        )

    suspend fun retrieveFleetMovementDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<FleetMovementRetrieveItem>> =
        Either.Right(
            fleetApi.retrieveFleetMovementDetails(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id = id
            )
        )

    suspend fun retrieveFleetMovementList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iWorkflowStatusID: Int
    ): Either<Failure, List<FleetMovementRetrieveItem>> =
        Either.Right(
            fleetApi.retrieveFleetMovementList(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iWorkflowStatusID = iWorkflowStatusID
            )
        )

    suspend fun populateFleetMovementList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iWorkflowStatusID: Int
    ): Either<Failure, List<FleetMovementPopulateList>> =
        Either.Right(
            fleetApi.populateFleetMovementList(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iWorkflowStatusID = iWorkflowStatusID
            )
        )

    suspend fun setFleetMovementStatus(
        url: String,
        sAuthorization: String,
        iStatusID: Int,
        id: Int,
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.setFleetMovementStatus(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iStatusID = iStatusID,
                id = id
            )
        )
}