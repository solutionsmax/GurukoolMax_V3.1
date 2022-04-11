package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.AMEND_FLEET_BUS_ROUTE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.CHECK_DUPLICATE_FLEET_BUS_ROUTE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FETCH_FLEET_BUS_ROUTE_NAME
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_FLEET_BUS_ROUTE_NAME
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POST_FLEET_BUS_ROUTE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_RETRIEVE_STUDENT_RESERVATION_LIST
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_ROUTE_DETAILS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.SET_FLEET_BUS_ROUTE_STATUS
import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.FleetBusRouteList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.PopulateFleetBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PopulateBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PostBusRouteItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.RetrieveStudentReservationBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import javax.inject.Inject

class FleetBusRoutesRepository @Inject constructor(
    private val fleetApi: FleetApi
) {

    /**
     * Fleet Bus Routes
     */
    suspend fun postBusRoutesInfo(
        url: String,
        sAuthorization: String,
        postBusRouteItem: PostBusRouteItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.postBusRouteInfo(
                url + POST_FLEET_BUS_ROUTE,
                "${TokenConstants.BEARER} $sAuthorization",
                postBusRouteItem
            )
        )

    suspend fun amendBusRoutesInfo(
        url: String,
        sAuthorization: String,
        postBusRouteItem: PostBusRouteItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.amendBusRouteInfo(
                url + AMEND_FLEET_BUS_ROUTE,
                "${TokenConstants.BEARER} $sAuthorization",
                postBusRouteItem
            )
        )

    suspend fun setBusRouteStatus(
        url: String,
        sAuthorization: String,
        iStatusID: Int,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.setBusRouteStatus(
                url + SET_FLEET_BUS_ROUTE_STATUS,
                "${TokenConstants.BEARER} $sAuthorization",
                iStatusID,
                id
            )
        )

    suspend fun fetchBusRouteName(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.fetchBusRouteName(
                url + FETCH_FLEET_BUS_ROUTE_NAME,
                "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun checkDuplicateBusRoute(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        sRouteName: String
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.checkDuplicateBusRouteName(
                url + CHECK_DUPLICATE_FLEET_BUS_ROUTE,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                sRouteName
            )
        )

    suspend fun populateBusRouteName(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iStatusID: Int
    ): Either<Failure, List<PopulateBusRoutesItems>> =
        Either.Right(
            fleetApi.populateBusRouteName(
                url + POPULATE_FLEET_BUS_ROUTE_NAME,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iStatusID
            )
        )

    suspend fun retrieveBusRouteDetails(
        url: String,
        sAuthorization: String,
        id: Int
    ): Either<Failure, List<FleetBusRouteList>> =
        Either.Right(
            fleetApi.retrieveBusRouteDetails(
                url + RETRIEVE_FLEET_ROUTE_DETAILS,
                "${TokenConstants.BEARER} $sAuthorization",
                id
            )
        )

    suspend fun retrieveStudentReservationList(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iPickupRouteID: Int,
        iDropRouteID: Int,
        iStatusID: Int
    ): Either<Failure, List<RetrieveStudentReservationBusRoutesItems>> =
        Either.Right(
            fleetApi.retrieveStudentReservationList(
                url + RETRIEVE_FLEET_RETRIEVE_STUDENT_RESERVATION_LIST,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID, iSchoolID, iPickupRouteID, iDropRouteID, iStatusID
            )
        )

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

    suspend fun populateFleetBusRoutes(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iStatusID: Int,
    ): Either<Failure, MutableList<PopulateFleetBusRoutesItems>> =
        Either.Right(
            fleetApi.populateFleetBusRoutes(
                url, "${TokenConstants.BEARER} $sAuthorization", iGroupID, iSchoolID, iStatusID
            )
        )

}