package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.AMEND_FLEET_BUS_STOP
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_FLEET_BUS_STOP
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_FLEET_BUS_STOP
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POST_FLEET_BUS_STOP
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_BUS_STOPS_DETAILS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.SET_FLEET_BUS_STOP_STOP
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.PopulateFleetBusStopItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.PostFleetBusStopItems
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import javax.inject.Inject

class FleetBusStopRepository @Inject constructor(
    private val fleetApi: FleetApi
) {

    suspend fun postFleetBusStops(
        url: String,
        sToken: String,
        postFleetBusStopItems: PostFleetBusStopItems
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.postFleetBusStops(
                url = url + POST_FLEET_BUS_STOP,
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                postFleetBusStopItems = postFleetBusStopItems
            )
        )

    suspend fun amendFleetBusStops(
        url: String,
        sToken: String,
        postFleetBusStopItems: PostFleetBusStopItems
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.amendFleetBusStops(
                url = url + AMEND_FLEET_BUS_STOP,
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                postFleetBusStopItems = postFleetBusStopItems
            )
        )

    suspend fun setFleetBusStopStatus(
        url: String,
        sToken: String,
        iStatusID: Int,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.setFleetBusStopStatus(
                url = url + SET_FLEET_BUS_STOP_STOP,
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                iStatusID = iStatusID,
                id = id
            )
        )

    suspend fun checkDuplicateFleetBusStop(
        url: String,
        sToken: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        sStopName: String
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.checkDuplicateFleetBusStop(
                url = url + CHECK_DUPLICATE_FLEET_BUS_STOP,
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                iGroupID, iSchoolID, iRouteID, sStopName
            )
        )

    suspend fun populateFleetBusStopList(
        url: String,
        sToken: String,
        iGroupID: Int,
        iSchoolID: Int,
        iRouteID: Int,
        iEditMode: Int,
        iStatusID: Int
    ): Either<Failure, List<PopulateFleetBusStopItems>> =
        Either.Right(
            fleetApi.populateFleetBusStopList(
                url = url + POPULATE_FLEET_BUS_STOP,
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                iGroupID, iSchoolID, iRouteID, iEditMode, iStatusID
            )
        )

    suspend fun retrieveBusStopDetails(
        url: String,
        sToken: String,
        id: Int
    ): Either<Failure, List<FleetBusPickupPointsList>> =
        Either.Right(
            fleetApi.retrieveBusStopDetails(
                url = url + RETRIEVE_FLEET_BUS_STOPS_DETAILS,
                sAuthorization = "${TokenConstants.BEARER} $sToken",
                id = id
            )
        )

}