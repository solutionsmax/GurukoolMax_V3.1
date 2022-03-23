package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetPostPhotoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterRetrieveListItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import javax.inject.Inject

class FleetRegistrationRepository @Inject constructor(
    private val fleetApi: FleetApi
) {

    /**
     * Post Fleet Registration Info
     */
    suspend fun postRegisteredFleetInfo(
        url: String,
        sAuthorization: String,
        fleetRegistrationPostInfoItem: FleetRegisterPostInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.postRegisterFleetInfo(
                url, "${TokenConstants.BEARER} $sAuthorization", fleetRegistrationPostInfoItem
            )
        )

    /**
     * Amend Fleet Registration Info
     */
    suspend fun amendRegisteredFleetInfo(
        url: String,
        sAuthorization: String,
        fleetRegistrationPostInfoItem: FleetRegisterPostInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.amendRegisterFleetInfo(
                url, "${TokenConstants.BEARER} $sAuthorization", fleetRegistrationPostInfoItem
            )
        )

    /**
     * Check Duplicate Fleet Registration
     */
    suspend fun checkDuplicateFleetRegistration(
        url: String,
        sAuthorization: String,
        sRegistrationNumber: String
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.checkDuplicateFleetRegistration(
                url, "${TokenConstants.BEARER} $sAuthorization", 1, 1, sRegistrationNumber
            )
        )

    /**
     * Post Fleet photo
     */
    suspend fun postFleetPhoto(
        url: String,
        sAuthorization: String,
        fleetPostPhotoItem: FleetPostPhotoItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.postFleetPhoto(
                url, "${TokenConstants.BEARER} $sAuthorization", fleetPostPhotoItem
            )
        )

    /**
     * Retrieve Registered Fleet List
     */
    suspend fun retrieveRegisteredFleetList(
        url: String,
        sAuthorization: String,
        iWorkflowStatusID: Int
    ): Either<Failure, List<FleetRegisterRetrieveListItem>> =
        Either.Right(
            fleetApi.retrieveRegisteredFleetList(
                url, "${TokenConstants.BEARER} $sAuthorization", 1, 1, iWorkflowStatusID
            )
        )

    /**
     * Retrieve Registered Fleet Details
     */
    suspend fun retrieveRegisteredFleetDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<FleetRegisterRetrieveListItem>> =
        Either.Right(
            fleetApi.retrieveRegisteredFleetDetails(
                url, "${TokenConstants.BEARER} $sAuthorization", id
            )
        )

    /**
     * Fetch Registered Fleet Photo
     */
    suspend fun fetchRegisteredFleetPhoto(
        url: String,
        sAuthorization: String,
        iVehicleID: Int
    ): Either<Failure, String> =
        Either.Right(
            fleetApi.fetchRegisteredFleetPhoto(
                url, "${TokenConstants.BEARER} $sAuthorization", iVehicleID
            )
        )

    /**
     * Populate Registered Fleet List
     */
    suspend fun populateRegisteredFleetList(
        url: String,
        sAuthorization: String,
        iWorkflowStatusID: Int
    ): Either<Failure, MutableList<PopulateRegisteredFleetList>> =
        Either.Right(
            fleetApi.populateRegisteredFleetList(
                url, "${TokenConstants.BEARER} $sAuthorization", 1, 1, iWorkflowStatusID
            )
        )

    /**
     * Set registered Fleet Status
     */
    suspend fun setRegisteredFleetStatus(
        url: String,
        sAuthorization: String,
        iStatusID: Int,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.setRegisteredFleetStatus(
                url, "${TokenConstants.BEARER} $sAuthorization", iStatusID, id
            )
        )
}