package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsRetrieveItems
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import javax.inject.Inject

class FuelLogsRepository @Inject constructor(
    private val fleetApi: FleetApi
) {

    suspend fun postFuelLogs(
        url: String,
        sAuthorization: String,
        postFuelLogsItem: FuelLogsPostInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.postFuelLog(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                fuelLogsPostInfoItem = postFuelLogsItem
            )
        )

    suspend fun amendFuelLogs(
        url: String,
        sAuthorization: String,
        postFuelLogsItem: FuelLogsPostInfoItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.amendFuelLog(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                fuelLogsPostInfoItem = postFuelLogsItem
            )
        )

    suspend fun checkDuplicateFuelLogs(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iFleetID: Int,
        iOdometerID: Int
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.checkDuplicateFuelLog(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iFleetID = iFleetID,
                iOdometerID = iOdometerID
            )
        )

    suspend fun retrieveFuelLogDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<FuelLogsRetrieveItems>> =
        Either.Right(
            fleetApi.retrieveFuelLogsDetails(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                id = id
            )
        )

    suspend fun retrieveFuelLogList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iFleetID: Int,
        iWorkflowStatusID: Int
    ): Either<Failure, List<FuelLogsRetrieveItems>> =
        Either.Right(
            fleetApi.retrieveFuelLogsList(
                url = url,
                sAuthorization = "${TokenConstants.BEARER} $sAuthorization",
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iFleetID = iFleetID,
                iWorkflowStatusID = iWorkflowStatusID
            )
        )
}