package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetBusRouteList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import javax.inject.Inject

class FleetBusRoutesRepository @Inject constructor(
    private val fleetApi: FleetApi
) {

    suspend fun retrieveBusRoutesList(
        url: String,
        sAuthorization: String,
        iStatusID: Int
    ): Either<Failure, List<FleetBusRouteList>> =
        Either.Right(
            fleetApi.retrieveBusRouteList(
                url, "${TokenConstants.BEARER} $sAuthorization", 1, 1, iStatusID
            )
        )

    suspend fun retrieveBusStopsList(
        url: String,
        sAuthorization: String,
        iStatusID: Int
    ): Either<Failure, List<FleetBusPickupPointsList>> =
        Either.Right(
            fleetApi.retrieveBusStopList(
                url, "${TokenConstants.BEARER} $sAuthorization", 1, 1, iStatusID
            )
        )

    suspend fun retrieveBusPickupSchedule(
        url: String,
        sAuthorization: String,
        iRouteID: Int,
        iStatusID: Int
    ): Either<Failure, List<FleetPickupScheduleList>> =
        Either.Right(
            fleetApi.retrieveBusPickupSchedule(
                url, "${TokenConstants.BEARER} $sAuthorization", 1, 1,
                iRouteID, iStatusID
            )
        )

}